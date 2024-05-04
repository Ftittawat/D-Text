package com.senior.d_text.domain.usecase

import com.senior.d_text.data.model.notification.ReceiveNotification
import com.senior.d_text.domain.repository.NotificationHistoryRepository

class SaveNotificationHistoryUseCase(private val notificationHistoryRepository: NotificationHistoryRepository) {
    suspend fun execute(receiveNotification: ReceiveNotification) = notificationHistoryRepository.saveHistory(receiveNotification)
}