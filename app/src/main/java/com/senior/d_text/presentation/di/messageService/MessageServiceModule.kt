package com.senior.d_text.presentation.di.messageService

import com.senior.d_text.domain.repository.MessageRepository
import com.senior.d_text.domain.usecase.SaveMessageUseCase
import dagger.Module
import dagger.Provides

@Module
class MessageServiceModule {
    @Provides
    fun provideSaveMessage(messageRepository: MessageRepository): SaveMessageUseCase {
        return SaveMessageUseCase(messageRepository)
    }
}
