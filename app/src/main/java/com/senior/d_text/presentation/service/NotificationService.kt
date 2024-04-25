package com.senior.d_text.presentation.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import androidx.core.app.NotificationCompat
import com.senior.d_text.R
import com.senior.d_text.data.repository.notificationReceiver.datasource.NotificationReceiverDatasource
import com.senior.d_text.domain.repository.NotificationReceiverRepository
import com.senior.d_text.domain.usecase.ListenForNotificationUseCase
import com.senior.d_text.presentation.di.Injector
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

//class NotificationService: NotificationListenerService() {
//    override fun onNotificationPosted(sbn: StatusBarNotification?) {
//        super.onNotificationPosted(sbn)
//        sbn?.let {
//            val packageName = it.packageName
//            val notification = it.notification
//        }
//    }
//
//    override fun onNotificationRemoved(sbn: StatusBarNotification?) {
//        super.onNotificationRemoved(sbn)
//    }
//}

class NotificationService : Service() {

    @Inject
    lateinit var listenForNotificationUseCase: ListenForNotificationUseCase

    override fun onCreate() {
        super.onCreate()
        (application as Injector).createNotificationService().inject(this)
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
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        handleNotifications()
        return START_STICKY
    }

    private fun handleNotifications() {
        CoroutineScope(Dispatchers.Main).launch {
            val notification = listenForNotificationUseCase
            Log.d("logNoti", "${notification.invoke()}")
        }
    }
}