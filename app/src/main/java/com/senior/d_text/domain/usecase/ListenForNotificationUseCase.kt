package com.senior.d_text.domain.usecase

import com.senior.d_text.data.model.notification.ReceiveNotification
import com.senior.d_text.domain.repository.NotificationReceiverRepository

class ListenForNotificationUseCase(
    private val notificationReceiverRepository: NotificationReceiverRepository
    ) {
    operator fun invoke(listener: (ReceiveNotification) -> Unit) {
        notificationReceiverRepository.listenForNotification(listener)
    }
}