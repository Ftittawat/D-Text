package com.senior.d_text.presentation.di.authentication.signin

import android.app.Application
import com.senior.d_text.domain.usecase.SignInUseCase
import com.senior.d_text.presentation.core.SignInViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class SignInModule {
    @SignInScope
    @Provides
    fun provideSignInViewModelFactory(
        application: Application,
        signInUseCase: SignInUseCase
    ): SignInViewModelFactory {
        return SignInViewModelFactory(
            application,
            signInUseCase
        )
    }
}