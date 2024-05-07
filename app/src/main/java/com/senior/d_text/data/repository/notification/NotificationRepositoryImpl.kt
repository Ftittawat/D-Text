package com.senior.d_text.data.repository.notification

import com.senior.d_text.data.model.notification.Notification
import com.senior.d_text.data.repository.notification.datasource.NotificationLocalDatasource
import com.senior.d_text.domain.repository.NotificationRepository
import kotlinx.coroutines.flow.Flow

class NotificationRepositoryImpl(
    private val notificationLocalDatasource: NotificationLocalDatasource
): NotificationRepository {
    override fun getNotification(): Flow<List<Notification>> {
        return notificationLocalDatasource.getNotificationFromDB()
    }

    override suspend fun saveNotification(notification: Notification) {
        notificationLocalDatasource.saveNotification(notification)
    }

    override suspend fun deleteAllNotification() {
        notificationLocalDatasource.clearAll()
    }

    override suspend fun deleteNotification(id: Int) {
        notificationLocalDatasource.deleteNotification(id)
    }
}