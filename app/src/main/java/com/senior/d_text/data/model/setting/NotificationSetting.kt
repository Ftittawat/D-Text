package com.senior.d_text.data.model.setting

data class NotificationSetting(
    val notification: Boolean,
    val unsafe: Boolean,
    val suspicious: Boolean,
    val safe: Boolean,
    val no_information: Boolean
)
