package com.senior.d_text.domain.repository

import com.senior.d_text.data.model.notification.ReceiveNotification

interface NotificationReceiverRepository {
    suspend fun getNotification(): List<ReceiveNotification>
}