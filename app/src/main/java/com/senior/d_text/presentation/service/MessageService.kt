package com.senior.d_text.presentation.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.senior.d_text.R
import com.senior.d_text.data.model.message.Message
import com.senior.d_text.data.model.message.ReceiveSMS
import com.senior.d_text.data.model.setting.NotificationSetting
import com.senior.d_text.data.repository.sms.SMSRepositoryImpl
import com.senior.d_text.domain.repository.SMSRepository
import com.senior.d_text.domain.usecase.AnalysisUrlMessageServiceUseCase
import com.senior.d_text.domain.usecase.AnalysisUrlUseCase
import com.senior.d_text.domain.usecase.ListenForMessagesUseCase
import com.senior.d_text.domain.usecase.SaveMessageUseCase
import com.senior.d_text.presentation.authentication.signin.SignInViewModel
import com.senior.d_text.presentation.core.MessageServiceViewModelFactory
import com.senior.d_text.presentation.di.Injector
import com.senior.d_text.presentation.home.HomeActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class MessageService : Service() {

    lateinit var listenForMessagesUseCase: ListenForMessagesUseCase
    private lateinit var smsRepository: SMSRepository
    @Inject
    lateinit var saveMessageUseCase: SaveMessageUseCase
    @Inject
    lateinit var analysisUrlMessageServiceUseCase: AnalysisUrlMessageServiceUseCase

    private var lastMessage: ReceiveSMS? = null
    private var newMessage: ReceiveSMS? = null

    override fun onCreate() {
        super.onCreate()
        (application as Injector).createMessageService()
            .inject(this)
        smsRepository = SMSRepositoryImpl(applicationContext)
        listenForMessagesUseCase = ListenForMessagesUseCase(smsRepository)

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
                lastMessage = message
                Log.d("logMessages", "messagesSender: ${lastMessage!!.sender}")
                Log.d("logMessages", "messagesBody: ${lastMessage!!.messageBody}")
                saveMessageHistory(lastMessage!!)
                checkUrl(lastMessage!!.url)
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
        val notificationSetting = loadNotificationSetting()
        val analysisResult = analysisUrlMessageServiceUseCase.execute(url)!!
        if (analysisResult.urlType == SAFE && notificationSetting.safe) {
            notify(analysisResult.urlType, analysisResult.url)
        }
        else if (analysisResult.urlType == SUSPICIOUS && notificationSetting.suspicious) {
            notify(analysisResult.urlType, analysisResult.url)
        }
        else if (analysisResult.urlType == UNSAFE && notificationSetting.unsafe) {
            notify(analysisResult.urlType, analysisResult.url)
        }
        else if (analysisResult.urlType == NO_INFORMATION && notificationSetting.no_information) {
            notify(analysisResult.urlType, analysisResult.url)
        }
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
            "safe" -> getString(R.string.notification_safe_title)
            "suspicious" -> getString(R.string.notification_suspicious_title)
            "unsafe" -> getString(R.string.notification_unsafe_title)
            else -> getString(R.string.notification_no_info_title)
        }

        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.logo_blue)
            .setContentTitle(title)
            .setContentText(url)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        val intent = Intent(this, HomeActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        notificationBuilder.setContentIntent(pendingIntent)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(notificationId, notificationBuilder.build())
    }

    private fun loadNotificationSetting(): NotificationSetting {
        val sharePref = application.getSharedPreferences("Setting_Notification", AppCompatActivity.MODE_PRIVATE)
        val safe = sharePref.getBoolean(SAFE, false)
        val suspicious = sharePref.getBoolean(SUSPICIOUS, false)
        val unsafe = sharePref.getBoolean(UNSAFE, false)
        val noInformation = sharePref.getBoolean(NO_INFORMATION, false)
        return NotificationSetting(unsafe, suspicious, safe, noInformation)
    }

    private fun saveMessageHistory(message: ReceiveSMS) {
        val saveMessage = Message(0, message.sender, message.messageBody, message.dateTime)
        CoroutineScope(Dispatchers.IO).launch {
            saveMessageUseCase.execute(saveMessage)
        }
    }

    private fun testSaveMessageHistory() {
        val saveMessage = Message(0, "+66910363409", "message.messageBody message.messageBody message.messageBody message.messageBody message.messageBody","17/04/2024 12:30:21")
        CoroutineScope(Dispatchers.IO).launch {
            saveMessageUseCase.execute(saveMessage)
        }
    }

    companion object {
        const val NOTIFICATION = "notification"
        const val UNSAFE = "unsafe"
        const val SUSPICIOUS = "suspicious"
        const val SAFE = "safe"
        const val NO_INFORMATION = "no_information"
    }

}