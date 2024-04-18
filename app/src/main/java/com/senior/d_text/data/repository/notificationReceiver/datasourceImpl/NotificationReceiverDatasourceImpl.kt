package com.senior.d_text.data.repository.notificationReceiver.datasourceImpl

import com.senior.d_text.data.model.notification.ReceiveNotification
import com.senior.d_text.data.repository.notificationReceiver.datasource.NotificationReceiverDatasource

class NotificationReceiverDatasourceImpl(): NotificationReceiverDatasource {
    override suspend fun fetchNotifications(): List<ReceiveNotification> {
        return emptyList()
    }
}