package com.senior.d_text.domain.usecase

import com.senior.d_text.domain.repository.NotificationRepository

class DeleteNotificationUseCase(private val notificationRepository: NotificationRepository) {
    suspend fun execute(id: Int) = notificationRepository.deleteNotification(id)
}