package com.senior.d_text.data.repository.notificationReceiver.datasource

import com.senior.d_text.data.model.notification.ReceiveNotification

interface NotificationReceiverDatasource {
    suspend fun fetchNotifications(): List<ReceiveNotification>
}