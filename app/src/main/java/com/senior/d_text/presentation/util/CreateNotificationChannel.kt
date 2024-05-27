package com.senior.d_text.presentation.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

interface NotificationChannelCreator {
    fun createNotificationChannel(channelId: String, channelName: String)
    fun createNotificationChannel(channelId: String)
}

class NotificationFactory(private val context: Context): NotificationChannelCreator {
    override fun createNotificationChannel(channelId: String, channelName: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val notificationManager: NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            channel.enableVibration(true)
            notificationManager.createNotificationChannel(channel)
        }
    }

    override fun createNotificationChannel(channelId: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                channelId,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val notificationManager: NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            channel.enableVibration(true)
            notificationManager.createNotificationChannel(channel)
        }
    }
}