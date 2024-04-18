package com.senior.d_text.data.model.notification

data class ReceiveNotification(
    val appName: String,
    val messageBody: String,
    var dateTime: String
)
