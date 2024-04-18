package com.senior.d_text.presentation.di.notificationService

import com.senior.d_text.domain.repository.NotificationReceiverRepository
import com.senior.d_text.domain.usecase.ListenForNotificationUseCase
import dagger.Module
import dagger.Provides

@Module
class NotificationServiceModule {
    @Provides
    fun provideListenForNotificationUseCase(
        notificationReceiverRepository: NotificationReceiverRepository
    ): ListenForNotificationUseCase {
        return ListenForNotificationUseCase((notificationReceiverRepository))
    }
}