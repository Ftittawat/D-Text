package com.senior.d_text.presentation.service

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.IBinder
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.senior.d_text.R
import com.senior.d_text.data.model.Result
import com.senior.d_text.data.model.analysis.AnalysisUrl
import com.senior.d_text.data.model.authentication.UserToken
import com.senior.d_text.data.model.notification.Notification
import com.senior.d_text.data.model.notification.ReceiveNotification
import com.senior.d_text.data.model.setting.NotificationSetting
import com.senior.d_text.data.repository.notificationReceiver.datasource.NotificationReceiverDatasource
import com.senior.d_text.domain.repository.NotificationReceiverRepository
import com.senior.d_text.domain.usecase.AnalysisUrlNotificationServiceUseCase
import com.senior.d_text.domain.usecase.ListenForNotificationUseCase
import com.senior.d_text.domain.usecase.SaveNotificationForNotificationUseCase
import com.senior.d_text.domain.usecase.SaveNotificationHistoryUseCase
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

class NotificationService : Service() {

    @Inject
    lateinit var listenForNotificationUseCase: ListenForNotificationUseCase
    @Inject
    lateinit var saveNotificationHistoryUseCase: SaveNotificationHistoryUseCase
    @Inject
    lateinit var saveNotificationForNotificationUseCase: SaveNotificationForNotificationUseCase
    @Inject
    lateinit var analysisUrlNotificationServiceUseCase: AnalysisUrlNotificationServiceUseCase

    private lateinit var userToken: UserToken
    private var lastNotification: ReceiveNotification? = null
    private var error: String = ""

    override fun onCreate() {
        super.onCreate()
        (application as Injector).createNotificationService().inject(this)
        userToken = loadUserToken()

//        val channelId = "NotificationForegroundServiceChannel"
        val groupKey = "AutoDetect"
//        val channel = NotificationChannel(
//            channelId,
//            "Notification Foreground Service Channel",
//            NotificationManager.IMPORTANCE_DEFAULT
//        )
//        val notificationManager: NotificationManager =
//            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//        notificationManager.createNotificationChannel(channel)
        val notificationBuilder = NotificationCompat.Builder(this, "AutoDetectionService")
            .setContentTitle(getString(R.string.notification_auto_scan_title))
            .setContentText(getString(R.string.notification_auto_scan_des))
            .setSmallIcon(R.drawable.logo_blue)
            .setGroup(groupKey)
        val notification = notificationBuilder.build()
        startForeground(2, notification)
        //val intent = Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS")
        //startActivity(intent)

        val n = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!n.isNotificationPolicyAccessGranted) {
                val intent = Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS")
                startActivity(intent)
            }
        }
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("NotiResult", "onStartCommand: ")
        startListeningForNotification()
        return START_STICKY
    }

    private fun startListeningForNotification() {
        listenForNotificationUseCase { notification ->
            if (lastNotification == null || notification.text != lastNotification!!.text) {
                lastNotification = notification
                Log.d("NotiResult", "Notification: ${notification.appName}")
                Log.d(
                    "NotiResult",
                    "Notification: ${notification.title}: ${notification.text}: ${notification.url}"
                )
                saveNotificationHistory(lastNotification!!)
                checkUrl(lastNotification!!.url)
            }
        }
    }

    private fun checkUrl(url: String) = GlobalScope.launch(Dispatchers.IO) {
        val response = analysisUrlNotificationServiceUseCase.execute(url, userToken.access_token!!)
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
        val notificationSetting = loadNotificationSetting()
        val checklist = result.dtextResult
        val google = result.webriskResult
        val type = CalculateUrlType().calculate(checklist.urlType, google.urlType)
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val currentTime = LocalDateTime.now().format(formatter)
        if (type == SAFE && notificationSetting.safe) {
            notify(type, checklist.url)
        }
        else if (type == SUSPICIOUS && notificationSetting.suspicious) {
            notify(type, checklist.url)
        }
        else if (type == UNSAFE && notificationSetting.unsafe) {
            notify(type, checklist.url)
        }
        else if (type == NO_INFORMATION && notificationSetting.no_information) {
            notify(type, checklist.url)
        }
        val notificationData = Notification(0, checklist.url, type, getString(R.string.source_message), getString(R.string.source_message), currentTime)
        saveNotification(notificationData)
    }

    private fun notify(urlType: String, url: String) {
        val notificationId = 1102
        val channelId = "AutoDetectionService"
        val title = when (urlType) {
            MessageService.SAFE -> getString(R.string.notification_safe_title)
            MessageService.SUSPICIOUS -> getString(R.string.notification_suspicious_title)
            MessageService.UNSAFE -> getString(R.string.notification_unsafe_title)
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

    private fun loadUserToken(): UserToken {
        val sharePref = applicationContext.getSharedPreferences("account", Context.MODE_PRIVATE)
        val tokenId = sharePref.getString("token_id", "")
        val accessToken = sharePref.getString("access_token", "")
        val refreshToken = sharePref.getString("refresh_token", "")
        return UserToken(tokenId, accessToken, refreshToken)
    }

    private fun loadNotificationSetting(): NotificationSetting {
        val sharePref = application.getSharedPreferences("Setting_Notification", AppCompatActivity.MODE_PRIVATE)
        val safe = sharePref.getBoolean(MessageService.SAFE, false)
        val suspicious = sharePref.getBoolean(MessageService.SUSPICIOUS, false)
        val unsafe = sharePref.getBoolean(MessageService.UNSAFE, false)
        val noInformation = sharePref.getBoolean(MessageService.NO_INFORMATION, false)
        return NotificationSetting(unsafe, suspicious, safe, noInformation)
    }

    private fun saveNotificationHistory(notification: ReceiveNotification) {
        val saveNotification = ReceiveNotification(0, notification.appName, notification.title, notification.text, notification.url, notification.dateTime)
        CoroutineScope(Dispatchers.IO).launch {
            saveNotificationHistoryUseCase.execute(saveNotification)
        }
    }

    private fun saveNotification(notification: Notification) {
        CoroutineScope(Dispatchers.IO).launch {
            saveNotificationForNotificationUseCase.execute(notification)
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