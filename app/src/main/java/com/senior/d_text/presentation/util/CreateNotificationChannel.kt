package com.senior.d_text.presentation.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context

interface NotificationChannelCreator {
    fun createNotificationChannel(channelId: String, channelName: String)
    fun createNotificationChannel(channelId: String)
}

class NotificationFactory(private val context: Context): NotificationChannelCreator {
    override fun createNotificationChannel(channelId: String, channelName: String) {
        val channel = NotificationChannel(
            channelId,
            channelName,
            NotificationManager.IMPORTANCE_DEFAULT
        )
        val notificationManager: NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    override fun createNotificationChannel(channelId: String) {
        val channel = NotificationChannel(
            channelId,
            channelId,
            NotificationManager.IMPORTANCE_DEFAULT
        )
        val notificationManager: NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}