package com.senior.d_text.data.repository.notificationReceiver

import com.senior.d_text.data.model.notification.ReceiveNotification
import com.senior.d_text.data.repository.notificationReceiver.datasource.NotificationReceiverDatasource
import com.senior.d_text.domain.repository.NotificationReceiverRepository

class NotificationReceiverRepositoryImpl(
    private val notificationReceiverDatasource: NotificationReceiverDatasource
): NotificationReceiverRepository {
    override suspend fun getNotification(): List<ReceiveNotification> {
        return notificationReceiverDatasource.fetchNotifications()
    }
}