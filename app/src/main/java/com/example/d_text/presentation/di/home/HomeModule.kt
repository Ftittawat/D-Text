package com.example.d_text.presentation.di.home

import com.example.d_text.presentation.core.HomeViewModelFactory
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