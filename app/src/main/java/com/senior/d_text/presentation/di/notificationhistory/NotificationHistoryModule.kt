package com.senior.d_text.presentation.di.notificationhistory

import com.senior.d_text.domain.usecase.DeleteAllNotificationHistoryUseCase
import com.senior.d_text.domain.usecase.GetNotificationHistoryUseCase
import com.senior.d_text.presentation.core.NotificationHistoryViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class NotificationHistoryModule {
    @NotificationHistoryScope
    @Provides
    fun provideNotificationHistoryViewModelFactory(
        getNotificationHistoryUseCase: GetNotificationHistoryUseCase,
        deleteAllNotificationHistoryUseCase: DeleteAllNotificationHistoryUseCase
    ): NotificationHistoryViewModelFactory {
        return NotificationHistoryViewModelFactory(
            getNotificationHistoryUseCase,
            deleteAllNotificationHistoryUseCase
        )
    }
}