package com.senior.d_text.presentation.di.core

import com.senior.d_text.domain.repository.HistoryRepository
import com.senior.d_text.domain.repository.MessageRepository
import com.senior.d_text.domain.repository.SMSRepository
import com.senior.d_text.domain.usecase.DeleteAllHistoryUseCase
import com.senior.d_text.domain.usecase.DeleteAllMessageUseCase
import com.senior.d_text.domain.usecase.ListenForMessagesUseCase
import com.senior.d_text.domain.usecase.GetHistoryUseCase
import com.senior.d_text.domain.usecase.GetMessageUseCase
import com.senior.d_text.domain.usecase.SaveHistoryUseCase
import com.senior.d_text.domain.usecase.SaveMessageUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun provideListenForMessagesUseCase(smsRepository: SMSRepository): ListenForMessagesUseCase {
        return ListenForMessagesUseCase((smsRepository))
    }

    @Provides
    fun provideGetHistoryUseCase(historyRepository: HistoryRepository): GetHistoryUseCase {
        return GetHistoryUseCase(historyRepository)
    }

    @Provides
    fun provideSaveHistoryUseCase(historyRepository: HistoryRepository): SaveHistoryUseCase {
        return SaveHistoryUseCase(historyRepository)
    }

    @Provides
    fun provideDeleteAllHistoryUseCase(historyRepository: HistoryRepository): DeleteAllHistoryUseCase {
        return DeleteAllHistoryUseCase(historyRepository)
    }

//    @Provides
//    fun provideSaveMessage(messageRepository: MessageRepository): SaveMessageUseCase {
//        return SaveMessageUseCase(messageRepository)
//    }

    @Provides
    fun provideGetMessageUseCase(messageRepository: MessageRepository): GetMessageUseCase {
        return GetMessageUseCase(messageRepository)
    }

    @Provides
    fun provideDeleteAllMessageUseCase(messageRepository: MessageRepository): DeleteAllMessageUseCase {
        return DeleteAllMessageUseCase(messageRepository)
    }
}