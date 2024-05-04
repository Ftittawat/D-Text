package com.senior.d_text.presentation.di.authentication

import com.senior.d_text.presentation.authentication.AuthenticationActivity
import dagger.Subcomponent

@AuthenticationScope
@Subcomponent(modules = [AuthenticationModule::class])
interface AuthenticationSubcomponent {
    fun inject(authenticationActivity: AuthenticationActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create(): AuthenticationSubcomponent
    }
}