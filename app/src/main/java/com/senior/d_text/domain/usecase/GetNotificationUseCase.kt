package com.senior.d_text.domain.usecase

import com.senior.d_text.data.model.notification.Notification
import com.senior.d_text.domain.repository.NotificationRepository
import kotlinx.coroutines.flow.Flow

class GetNotificationUseCase(private val notificationRepository: NotificationRepository) {
    fun execute():Flow<List<Notification>>? = notificationRepository.getNotification()
}