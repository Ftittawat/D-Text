package com.senior.d_text.presentation.di.authentication.signup

import android.app.Application
import com.senior.d_text.domain.usecase.SignUpUseCase
import com.senior.d_text.presentation.core.SignUpViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class SignUpModule {
    @SignUpScope
    @Provides
    fun provideSignUpViewModelFactory(
        application: Application,
        signUpUseCase: SignUpUseCase
    ): SignUpViewModelFactory{
        return SignUpViewModelFactory(
            application,
            signUpUseCase
        )
    }
}