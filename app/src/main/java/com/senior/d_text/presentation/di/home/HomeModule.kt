package com.senior.d_text.presentation.di.home

import com.senior.d_text.presentation.core.HomeViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class HomeModule {
    @HomeScope
    @Provides
    fun provideMovieViewModelFactory(): HomeViewModelFactory {
        return HomeViewModelFactory()
    }

}