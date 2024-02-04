package com.example.d_text.presentation.di

import android.content.Context
import dagger.Provides
import javax.inject.Singleton

class AppModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideApplicationContext() : Context {
        return context.applicationContext
    }
}