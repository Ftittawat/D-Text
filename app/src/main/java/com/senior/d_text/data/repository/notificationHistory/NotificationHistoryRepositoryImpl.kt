package com.senior.d_text.data.repository.notificationHistory

import com.senior.d_text.data.model.notification.ReceiveNotification
import com.senior.d_text.data.repository.notificationHistory.datasource.NotificationHistoryLocalDatasource
import com.senior.d_text.domain.repository.NotificationHistoryRepository
import kotlinx.coroutines.flow.Flow

class NotificationHistoryRepositoryImpl(
    private val notificationHistoryLocalDatasource: NotificationHistoryLocalDatasource
): NotificationHistoryRepository {
    override fun getHistory(): Flow<List<ReceiveNotification>> {
        return notificationHistoryLocalDatasource.getHistoryFromDB()
    }

    override suspend fun saveHistory(receiveNotification: ReceiveNotification) {
        notificationHistoryLocalDatasource.saveHistory(receiveNotification)
    }

    override suspend fun deleteAllHistory() {
        notificationHistoryLocalDatasource.clearAll()
    }
}