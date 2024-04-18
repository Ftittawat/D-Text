package com.senior.d_text.presentation.di

import com.senior.d_text.presentation.di.home.HomeSubComponent
import com.senior.d_text.presentation.di.messagehistory.MessageHistorySubComponent
import com.senior.d_text.presentation.di.messageService.MessageServiceSubComponent
import com.senior.d_text.presentation.di.notificationService.NotificationServiceSubComponent

interface Injector {
    fun createHomeSubComponent(): HomeSubComponent
    fun createMessageHistorySubComponent(): MessageHistorySubComponent
    fun createMessageService(): MessageServiceSubComponent
    fun createNotificationService(): NotificationServiceSubComponent
}