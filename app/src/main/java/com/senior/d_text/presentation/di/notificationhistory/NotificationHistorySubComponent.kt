package com.senior.d_text.presentation.di.notificationhistory

import com.senior.d_text.presentation.setting.notificationhistory.NotificationHistoryActivity
import dagger.Subcomponent

@NotificationHistoryScope
@Subcomponent(modules = [NotificationHistoryModule::class])
interface NotificationHistorySubComponent {
    fun inject(notificationHistoryActivity: NotificationHistoryActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create():NotificationHistorySubComponent
    }
}