package com.senior.d_text.presentation.di.messagehistory

import com.senior.d_text.presentation.setting.messagehistory.MessageHistoryActivity
import dagger.Subcomponent

@MessageHistoryScope
@Subcomponent(modules = [MessageHistoryModule::class])
interface MessageHistorySubComponent {
    fun inject(messageHistoryActivity: MessageHistoryActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create():MessageHistorySubComponent
    }
}