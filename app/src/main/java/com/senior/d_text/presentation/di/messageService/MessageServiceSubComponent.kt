package com.senior.d_text.presentation.di.messageService

import com.senior.d_text.presentation.service.MessageService
import dagger.Subcomponent

@MessageServiceScope
@Subcomponent(modules = [MessageServiceModule::class])
interface MessageServiceSubComponent {
    fun inject(messageService: MessageService)

    @Subcomponent.Factory
    interface Factory {
        fun create(): MessageServiceSubComponent
    }
}