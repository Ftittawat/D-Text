package com.senior.d_text.presentation.di.authentication.signin

import com.senior.d_text.presentation.authentication.signin.SignInFragment
import dagger.Subcomponent
import javax.inject.Singleton

@SignInScope
@Subcomponent(modules = [SignInModule::class])
interface SignInSubComponent {
    fun inject(signInFragment: SignInFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): SignInSubComponent
    }
}