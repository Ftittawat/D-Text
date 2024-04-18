package com.senior.d_text.data.repository.notificationReceiver

import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification

class NotificationReceiver: NotificationListenerService() {
    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        super.onNotificationPosted(sbn)
        sbn?.let {
            val packageName = it.packageName
            val notification = it.notification
        }
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification?) {
        super.onNotificationRemoved(sbn)
    }
}