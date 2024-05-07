package com.senior.d_text.domain.usecase

import com.senior.d_text.data.model.notification.Notification
import com.senior.d_text.domain.repository.NotificationRepository

class SaveNotificationForMessageUseCase(private val notificationRepository: NotificationRepository) {
    suspend fun execute(notification: Notification) = notificationRepository.saveNotification(notification)
}