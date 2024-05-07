package com.senior.d_text.data.repository.notificationReceiver

import android.app.Notification
import android.content.Intent
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.senior.d_text.data.model.notification.ReceiveNotification
import com.senior.d_text.presentation.App
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class NotificationListener: NotificationListenerService() {

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        super.onNotificationPosted(sbn)
        sbn?.let {
            if (it.packageName != "com.senior.d_text" && it.packageName != "android") {
                val packageName = it.packageName
                val notification = it.notification
                val title = notification.extras.getString(Notification.EXTRA_TITLE) ?: ""
                val text = notification.extras.getString(Notification.EXTRA_TEXT) ?: ""
                if (text.isNotEmpty()) {
                    val msgIntent = Intent("com.d-text.ACTION_NOTIFICATION_INTENT")
                    msgIntent.putExtra("packageName", packageName)
                    msgIntent.putExtra("title", title)
                    msgIntent.putExtra("text", text)
                    Log.d("NotiResult", "onNotificationPosted: $packageName")
                    Log.d("NotiResult", "onNotificationPosted: $title")
                    Log.d("NotiResult", "onNotificationPosted: $text")
                    // LocalBroadcastManager.getInstance(applicationContext).sendBroadcast(msgIntent)
                    applicationContext.sendBroadcast(msgIntent)
                }
            }
        }
    }
}