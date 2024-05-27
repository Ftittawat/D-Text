package com.senior.d_text.presentation.util

import com.senior.d_text.presentation.service.NotificationService

class CalculateUrlType {

    fun calculate(d_text_type: String, google_type: String): String {
        return if (google_type == SAFE && (d_text_type == SAFE) || d_text_type == NO_INFORMATION) {
            SAFE
        } else if (google_type == SAFE && (d_text_type == UNSAFE || d_text_type == SUSPICIOUS)) {
            SUSPICIOUS
        } else if (google_type == UNSAFE) {
            UNSAFE
        } else {
            NO_INFORMATION
        }
    }

    companion object {
        const val NOTIFICATION = "notification"
        const val UNSAFE = "unsafe"
        const val SUSPICIOUS = "suspicious"
        const val SAFE = "safe"
        const val NO_INFORMATION = "noinformation"
    }
}