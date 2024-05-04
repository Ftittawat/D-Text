package com.senior.d_text.data.repository.notificationReceiver

import android.app.Notification
import android.content.Intent
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.senior.d_text.data.model.notification.ReceiveNotification
import com.senior.d_text.presentation.App

class NotificationListener: NotificationListenerService() {

//    private var listener: ((ReceiveNotification) -> Unit)? = null

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        super.onNotificationPosted(sbn)
        Log.d("NotiResult", "onNotificationPosted: ")
        sbn?.let {
            val packageName = it.packageName
            val notification = it.notification
            val title = notification.extras.getString(Notification.EXTRA_TITLE) ?: ""
            val text = notification.extras.getString(Notification.EXTRA_TEXT) ?: ""
//            listener?.invoke(ReceiveNotification(packageName, title, text, "2024-04-28"))
//            val receiveNotification = ReceiveNotification(packageName, title, text, "2024-04-28")
//            val notificationReceiverRepository = NotificationReceiverRepositoryImpl()
//            notificationReceiverRepository.getNotification(receiveNotification)
            val msg = Intent("com.senior.ACTION_CUSTOM_INTENT")
            msg.putExtra("packageName", packageName)
            msg.putExtra("title", title)
            msg.putExtra("text", text)
            Log.d("NotiResult", "onNotificationPosted: $packageName")
            Log.d("NotiResult", "onNotificationPosted: $title")
            Log.d("NotiResult", "onNotificationPosted: $text")
            LocalBroadcastManager.getInstance(applicationContext).sendBroadcast(msg)
        }
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification?) {
        super.onNotificationRemoved(sbn)
        Log.d("NotiResult", "onNotificationRemoved: ")
    }

//    fun setListener(listener: (ReceiveNotification) -> Unit) {
//        this.listener = listener
//    }
}