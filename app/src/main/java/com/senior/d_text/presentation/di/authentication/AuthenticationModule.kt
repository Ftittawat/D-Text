package com.senior.d_text.presentation.di.authentication

import com.senior.d_text.presentation.core.AuthenticationViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class AuthenticationModule {
    @AuthenticationScope
    @Provides
    fun provideAuthenticationViewModelFactory(

    ): AuthenticationViewModelFactory {
        return AuthenticationViewModelFactory()
    }
}