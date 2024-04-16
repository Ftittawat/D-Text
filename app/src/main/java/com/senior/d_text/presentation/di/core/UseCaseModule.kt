package com.senior.d_text.presentation.di.core

import com.senior.d_text.domain.repository.HistoryRepository
import com.senior.d_text.domain.repository.SMSRepository
import com.senior.d_text.domain.usecase.DeleteAllHistoryUseCase
import com.senior.d_text.domain.usecase.ListenForMessagesUseCase
import com.senior.d_text.domain.usecase.GetHistoryUseCase
import com.senior.d_text.domain.usecase.SaveHistoryUseCase
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
}