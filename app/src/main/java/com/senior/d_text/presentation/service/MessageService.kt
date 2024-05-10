package com.senior.d_text.presentation.service

import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.senior.d_text.R
import com.senior.d_text.data.model.Result
import com.senior.d_text.data.model.analysis.AnalysisUrl
import com.senior.d_text.data.model.authentication.UserToken
import com.senior.d_text.data.model.message.Message
import com.senior.d_text.data.model.message.ReceiveSMS
import com.senior.d_text.data.model.notification.Notification
import com.senior.d_text.data.model.setting.NotificationSetting
import com.senior.d_text.data.repository.sms.SMSRepositoryImpl
import com.senior.d_text.domain.repository.SMSRepository
import com.senior.d_text.domain.usecase.AnalysisUrlMessageServiceUseCase
import com.senior.d_text.domain.usecase.ListenForMessagesUseCase
import com.senior.d_text.domain.usecase.SaveMessageUseCase
import com.senior.d_text.domain.usecase.SaveNotificationForMessageUseCase
import com.senior.d_text.presentation.autoscan.AutoScanActivity
import com.senior.d_text.presentation.di.Injector
import com.senior.d_text.presentation.home.HomeActivity
import com.senior.d_text.presentation.home.HomeViewModel
import com.senior.d_text.presentation.util.CalculateUrlType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class MessageService : Service() {

    @Inject
    lateinit var listenForMessagesUseCase: ListenForMessagesUseCase
    @Inject
    lateinit var saveMessageUseCase: SaveMessageUseCase
    @Inject
    lateinit var saveNotificationForMessageUseCase: SaveNotificationForMessageUseCase
    @Inject
    lateinit var analysisUrlMessageServiceUseCase: AnalysisUrlMessageServiceUseCase

    private lateinit var apiKey: UserToken
    private var lastMessage: ReceiveSMS? = null
    private var error: String = ""

    override fun onCreate() {
        super.onCreate()
        (application as Injector).createMessageService()
            .inject(this)
        // smsRepository = SMSRepositoryImpl(applicationContext)
        // listenForMessagesUseCase = ListenForMessagesUseCase(smsRepository)
        apiKey = loadUserToken()

        Log.d("logMessages", "onCreate: MessageService")
//        val channelId = "MessageForegroundServiceChannel"
        val groupKey = "AutoDetect"
//        val channel = NotificationChannel(
//            channelId,
//            "Message Foreground Service Channel",
//            NotificationManager.IMPORTANCE_DEFAULT
//        )
//        val notificationManager: NotificationManager =
//            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//        notificationManager.createNotificationChannel(channel)
        val notificationBuilder = NotificationCompat.Builder(this, "AutoDetectionService")
            .setContentTitle(getString(R.string.message_auto_scan_title))
            .setContentText(getString(R.string.message_auto_scan_des))
            .setSmallIcon(R.drawable.logo_blue)
            .setGroup(groupKey)
        val notification = notificationBuilder.build()
        startForeground(1, notification)
    }

    override fun onBind(p0: Intent?): IBinder? {
        Log.d("logMessages", "onBind")
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        //stopSelf()
        Log.d("logMessages", "onStartCommand")
        startListeningForMessages()
        return START_STICKY
    }

    private fun startListeningForMessages() {
        listenForMessagesUseCase { message ->
            if (lastMessage == null || message.messageBody != lastMessage!!.messageBody) {
                if (message.url.isNotEmpty()) {
                    lastMessage = message
                    Log.d("logMessages", "messagesSender: ${lastMessage!!.sender}")
                    Log.d("logMessages", "messagesBody: ${lastMessage!!.messageBody}")
                    Log.d("logMessages", "url: ${lastMessage!!.url}")
                    saveMessageHistory(lastMessage!!)
                    checkUrl(lastMessage!!.url)
                }
            }
            // lastMessage.sender = message.sender
            // lastMessage.messageBody = message.messageBody
            // val currentMessages = messages.toMutableList()
            // currentMessages.add(message)
            // Log.d("sms", "startListeningForMessages: $currentMessages")
            // Log.d("sms", "startListeningForMessages: ${_messages.value}")

        }
    }

    private fun checkUrl(url: String) = GlobalScope.launch(Dispatchers.IO) {
        // Log.d("logMessages", "checkUrl: $url")
        val response = analysisUrlMessageServiceUseCase.execute(url, apiKey.access_token!!)
        when (response) {
            is Result.Success -> {
                val data = response.data!!
                displayResult(data)
            }
            is Result.Error -> {
                error = response.message
            }
        }
    }

    private fun displayResult(result: AnalysisUrl) {
        // Log.d("logMessages", "displayResult: ")
        val notificationSetting = loadNotificationSetting()
        val checklist = result.dtextResult
        val google = result.webriskResult
        val riskLevel = CalculateUrlType().calculate(checklist.urlType, google.urlType)
        val orgName = validateOrgName(checklist.organzName, checklist.registrarName)
        val type = checkOrgName(riskLevel, orgName, google.urlThreatType)
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val currentTime = LocalDateTime.now().format(formatter)
        Log.d("logMessages", "displayResult: ${checklist.url} $riskLevel")
        if (riskLevel == SAFE && notificationSetting.notification && notificationSetting.safe) {
            notify(riskLevel, checklist.url)
        }
        else if (riskLevel == SUSPICIOUS && notificationSetting.notification && notificationSetting.suspicious) {
            notify(riskLevel, checklist.url)
        }
        else if (riskLevel == UNSAFE && notificationSetting.notification && notificationSetting.unsafe) {
            notify(riskLevel, checklist.url)
        }
        else if (riskLevel == NO_INFORMATION && notificationSetting.notification && notificationSetting.no_information) {
            notify(riskLevel, checklist.url)
        }
        val notificationData = Notification(
            0,
            checklist.url,
            riskLevel,
            checklist.urlType,
            google.urlType,
            type,
            getString(R.string.source_message),
            getString(R.string.source_message),
            currentTime,
            checklist.domainAgeDay,
            checklist.hasForm,
            checklist.hasIframe,
            checklist.hasShortened,
            checklist.hasSsl,
            checklist.urlScore)
        saveNotification(notificationData)
    }

//    fun checkUrl() = liveData {
//        val analysisResult = analysisUrlUseCase.execute(url.value.toString())
//        Log.d("analysisResult", "checkUrl: $analysisResult")
//        emit(analysisResult)
//    }

    private fun notify(urlType: String, url: String) {
        val notificationId = 1101
        val channelId = "AutoDetectionService"
        val title = when (urlType) {
            SAFE -> getString(R.string.notification_safe_title)
            SUSPICIOUS -> getString(R.string.notification_suspicious_title)
            UNSAFE -> getString(R.string.notification_unsafe_title)
            else -> getString(R.string.notification_no_info_title)
        }

        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.logo_blue)
            .setContentTitle(title)
            .setContentText(url)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        val intent = Intent(this, AutoScanActivity::class.java)
        // intent.putExtra("url", url)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        notificationBuilder.setContentIntent(pendingIntent)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(notificationId, notificationBuilder.build())
    }

    private fun loadNotificationSetting(): NotificationSetting {
        val sharePref =
            application.getSharedPreferences("Setting_Notification", AppCompatActivity.MODE_PRIVATE)
        val notification = sharePref.getBoolean(NOTIFICATION, false)
        val safe = sharePref.getBoolean(SAFE, false)
        val suspicious = sharePref.getBoolean(SUSPICIOUS, false)
        val unsafe = sharePref.getBoolean(UNSAFE, false)
        val noInformation = sharePref.getBoolean(NO_INFORMATION, false)
        return NotificationSetting(notification, unsafe, suspicious, safe, noInformation)
    }

    private fun loadUserToken(): UserToken {
        val sharePref = applicationContext.getSharedPreferences("account", Context.MODE_PRIVATE)
        val tokenId = sharePref.getString("token_id", "")
        val accessToken = sharePref.getString("access_token", "")
        val refreshToken = sharePref.getString("refresh_token", "")
        return UserToken(tokenId, accessToken, refreshToken)
    }

    private fun saveMessageHistory(message: ReceiveSMS) {
        val saveMessage = Message(0, message.sender, message.messageBody, message.url, message.dateTime)
        CoroutineScope(Dispatchers.IO).launch {
            saveMessageUseCase.execute(saveMessage)
        }
    }

    private fun saveNotification(notification: Notification) {
        CoroutineScope(Dispatchers.IO).launch {
            saveNotificationForMessageUseCase.execute(notification)
        }
    }

    private fun validateOrgName(org_name: String, registrarName: String): String {
        return if (org_name.isNullOrEmpty()) {
            ""
        } else {
            org_name
        }
    }

    private fun checkOrgName(url_type: String, org_name: String, threat_type: String): String {
        return if (url_type == HomeViewModel.UNSAFE) {
            threat_type
        } else {
            org_name
        }
    }

    companion object {
        const val NOTIFICATION = "notification"
        const val UNSAFE = "unsafe"
        const val SUSPICIOUS = "suspicious"
        const val SAFE = "safe"
        const val NO_INFORMATION = "noinformation"
    }

}