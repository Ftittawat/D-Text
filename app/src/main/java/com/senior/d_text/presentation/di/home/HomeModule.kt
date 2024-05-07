package com.senior.d_text.presentation.di.home

import android.app.Application
import com.senior.d_text.domain.usecase.AnalysisUrlUseCase
import com.senior.d_text.domain.usecase.DeleteAllHistoryUseCase
import com.senior.d_text.domain.usecase.DeleteHistoryUseCase
import com.senior.d_text.domain.usecase.GetHistoryUseCase
import com.senior.d_text.domain.usecase.ListenForMessagesUseCase
import com.senior.d_text.domain.usecase.RefreshUseCase
import com.senior.d_text.domain.usecase.SaveHistoryUseCase
import com.senior.d_text.presentation.core.HomeViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class HomeModule {
    @HomeScope
    @Provides
    fun provideHomeViewModelFactory(
        application: Application,
        listenForMessagesUseCase : ListenForMessagesUseCase,
        getHistoryUseCase: GetHistoryUseCase,
        saveHistoryUseCase: SaveHistoryUseCase,
        deleteAllHistoryUseCase: DeleteAllHistoryUseCase,
        deleteHistoryUseCase: DeleteHistoryUseCase,
        analysisUrlUseCase: AnalysisUrlUseCase,
        refreshUseCase: RefreshUseCase
    ): HomeViewModelFactory {
        return HomeViewModelFactory(
            application,
            listenForMessagesUseCase,
            getHistoryUseCase,
            saveHistoryUseCase,
            deleteAllHistoryUseCase,
            deleteHistoryUseCase,
            analysisUrlUseCase,
            refreshUseCase
        )
    }

}