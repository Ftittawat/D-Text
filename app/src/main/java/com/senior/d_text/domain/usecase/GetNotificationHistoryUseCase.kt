package com.senior.d_text.domain.usecase

import com.senior.d_text.data.model.notification.ReceiveNotification
import com.senior.d_text.domain.repository.NotificationHistoryRepository
import kotlinx.coroutines.flow.Flow

class GetNotificationHistoryUseCase(private val notificationHistoryRepository: NotificationHistoryRepository) {
    fun execute(): Flow<List<ReceiveNotification>>? = notificationHistoryRepository.getHistory()
}