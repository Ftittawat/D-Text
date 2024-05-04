package com.senior.d_text.domain.usecase

import com.senior.d_text.domain.repository.NotificationHistoryRepository

class DeleteAllNotificationHistoryUseCase(private val notificationHistoryRepository: NotificationHistoryRepository) {
    suspend fun execute() = notificationHistoryRepository.deleteAllHistory()
}