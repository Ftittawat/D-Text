package com.senior.d_text.data.repository.notificationHistory.datasourceImpl

import com.senior.d_text.data.db.NotificationHistoryDao
import com.senior.d_text.data.model.notification.ReceiveNotification
import com.senior.d_text.data.repository.notificationHistory.datasource.NotificationHistoryLocalDatasource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class NotificationHistoryLocalDatasourceImpl(
    private val notificationHistoryDao: NotificationHistoryDao
    ): NotificationHistoryLocalDatasource {
    override suspend fun saveHistory(receiveNotification: ReceiveNotification) {
        CoroutineScope(Dispatchers.IO).launch {
            notificationHistoryDao.saveHistory(receiveNotification)
        }
    }

    override fun getHistoryFromDB(): Flow<List<ReceiveNotification>> {
        return notificationHistoryDao.getHistory()
    }

    override suspend fun clearAll() {
        CoroutineScope(Dispatchers.IO).launch {
            notificationHistoryDao.deleteAllHistory()
        }
    }
}