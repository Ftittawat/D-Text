package com.senior.d_text.domain.usecase

import com.senior.d_text.domain.repository.NotificationRepository

class DeleteAllNotificationUseCase(private val notificationRepository: NotificationRepository) {
    suspend fun execute() = notificationRepository.deleteAllNotification()
}