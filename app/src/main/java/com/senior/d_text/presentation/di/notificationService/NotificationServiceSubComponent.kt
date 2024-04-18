package com.senior.d_text.presentation.di.notificationService

import com.senior.d_text.presentation.service.NotificationService
import dagger.Subcomponent

@NotificationServiceScope
@Subcomponent(modules = [NotificationServiceModule::class])
interface NotificationServiceSubComponent {
    fun inject(notificationService: NotificationService)

    @Subcomponent.Factory
    interface Factory {
        fun create(): NotificationServiceSubComponent
    }
}