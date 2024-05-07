package com.senior.d_text.domain.repository

import com.senior.d_text.data.model.notification.Notification
import kotlinx.coroutines.flow.Flow

interface NotificationRepository {
    fun getNotification(): Flow<List<Notification>>?
    suspend fun saveNotification(notification: Notification)
    suspend fun deleteAllNotification()
    suspend fun deleteNotification(id: Int)
}