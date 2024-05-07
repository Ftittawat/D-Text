package com.senior.d_text.data.repository.notification.datasource

import com.senior.d_text.data.model.notification.Notification
import kotlinx.coroutines.flow.Flow

interface NotificationLocalDatasource {
    suspend fun saveNotification(notification: Notification)
    fun getNotificationFromDB(): Flow<List<Notification>>
    suspend fun clearAll()
    suspend fun deleteNotification(id: Int)
}