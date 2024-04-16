package com.senior.d_text.presentation.di.home

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
    fun provideMovieViewModelFactory(
        listenForMessagesUseCase : ListenForMessagesUseCase,
        getHistoryUseCase: GetHistoryUseCase,
        saveHistoryUseCase: SaveHistoryUseCase,
        deleteAllHistoryUseCase: DeleteAllHistoryUseCase
    ): HomeViewModelFactory {
        return HomeViewModelFactory(
            listenForMessagesUseCase,
            getHistoryUseCase,
            saveHistoryUseCase,
            deleteAllHistoryUseCase
        )
    }

}