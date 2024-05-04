package com.senior.d_text.presentation.di.authentication.signup

import com.senior.d_text.presentation.authentication.signup.SignUpFragment
import dagger.Subcomponent

@SignUpScope
@Subcomponent(modules = [SignUpModule::class])
interface SignUpSubComponent {
    fun inject(signUpFragment: SignUpFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create() :SignUpSubComponent
    }
}