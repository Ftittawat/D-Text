package com.senior.d_text.presentation.di.home

import com.senior.d_text.domain.usecase.AnalysisUrlUseCase
import com.senior.d_text.domain.usecase.DeleteAllHistoryUseCase
import com.senior.d_text.domain.usecase.GetHistoryUseCase
import com.senior.d_text.domain.usecase.ListenForMessagesUseCase
import com.senior.d_text.domain.usecase.SaveHistoryUseCase
import com.senior.d_text.presentation.core.HomeViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class HomeModule {
    @HomeScope
    @Provides
    fun provideHomeViewModelFactory(
        listenForMessagesUseCase : ListenForMessagesUseCase,
        getHistoryUseCase: GetHistoryUseCase,
        saveHistoryUseCase: SaveHistoryUseCase,
        deleteAllHistoryUseCase: DeleteAllHistoryUseCase,
        analysisUrlUseCase: AnalysisUrlUseCase
    ): HomeViewModelFactory {
        return HomeViewModelFactory(
            listenForMessagesUseCase,
            getHistoryUseCase,
            saveHistoryUseCase,
            deleteAllHistoryUseCase,
            analysisUrlUseCase
        )
    }

}