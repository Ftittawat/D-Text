package com.senior.d_text.data.repository.notification.datasourceImpl

import com.senior.d_text.data.db.NotificationDao
import com.senior.d_text.data.model.notification.Notification
import com.senior.d_text.data.repository.notification.datasource.NotificationLocalDatasource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class NotificationLocalDatasourceImpl(private val notificationDao: NotificationDao): NotificationLocalDatasource {
    override suspend fun saveNotification(notification: Notification) {
        CoroutineScope(Dispatchers.IO).launch {
            notificationDao.saveNotification(notification)
        }
    }

    override fun getNotificationFromDB(): Flow<List<Notification>> {
        return notificationDao.getNotification()
    }

    override suspend fun clearAll() {
        CoroutineScope(Dispatchers.IO).launch {
            notificationDao.deleteAllNotification()
        }
    }

    override suspend fun deleteNotification(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            notificationDao.deleteNotification(id)
        }
    }
}