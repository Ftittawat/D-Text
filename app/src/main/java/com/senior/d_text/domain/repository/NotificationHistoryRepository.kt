package com.senior.d_text.domain.repository

import com.senior.d_text.data.model.notification.ReceiveNotification
import kotlinx.coroutines.flow.Flow

interface NotificationHistoryRepository {
    fun getHistory(): Flow<List<ReceiveNotification>>
    suspend fun saveHistory(receiveNotification: ReceiveNotification)
    suspend fun deleteAllHistory()
}