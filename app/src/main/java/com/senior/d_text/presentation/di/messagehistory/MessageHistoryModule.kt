package com.senior.d_text.presentation.di.messagehistory

import com.senior.d_text.domain.usecase.DeleteAllMessageUseCase
import com.senior.d_text.domain.usecase.GetMessageUseCase
import com.senior.d_text.presentation.core.MessageHistoryViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class MessageHistoryModule {
    @MessageHistoryScope
    @Provides
    fun provideMessageHistoryViewModelFactory(
        getMessageUseCase: GetMessageUseCase,
        deleteAllMessageUseCase: DeleteAllMessageUseCase
    ): MessageHistoryViewModelFactory {
        return MessageHistoryViewModelFactory(
            getMessageUseCase,
            deleteAllMessageUseCase
        )
    }
}