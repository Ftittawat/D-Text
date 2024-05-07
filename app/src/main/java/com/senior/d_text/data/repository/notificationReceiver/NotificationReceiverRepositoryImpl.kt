package com.senior.d_text.data.repository.notificationReceiver

import android.content.Context
import android.content.IntentFilter
import android.util.Log
import com.senior.d_text.data.model.notification.ReceiveNotification
import com.senior.d_text.domain.repository.NotificationReceiverRepository

class NotificationReceiverRepositoryImpl(private val context: Context): NotificationReceiverRepository {
    override fun listenForNotification(listener: (ReceiveNotification) -> Unit) {
        val notificationReceiver = NotificationReceiver()
        context.registerReceiver(notificationReceiver, IntentFilter("com.d-text.ACTION_NOTIFICATION_INTENT"))
        notificationReceiver.setListener(listener)
    }

}