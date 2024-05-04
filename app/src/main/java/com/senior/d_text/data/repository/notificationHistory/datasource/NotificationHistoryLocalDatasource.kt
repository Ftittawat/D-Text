package com.senior.d_text.data.repository.notificationHistory.datasource

import com.senior.d_text.data.model.notification.ReceiveNotification
import kotlinx.coroutines.flow.Flow

interface NotificationHistoryLocalDatasource {
    suspend fun saveHistory(receiveNotification: ReceiveNotification)
    fun getHistoryFromDB(): Flow<List<ReceiveNotification>>
    suspend fun clearAll()
}