package com.senior.d_text.presentation.di

import com.senior.d_text.presentation.di.authentication.AuthenticationSubcomponent
import com.senior.d_text.presentation.di.authentication.signin.SignInSubComponent
import com.senior.d_text.presentation.di.authentication.signup.SignUpSubComponent
import com.senior.d_text.presentation.di.autoscan.AutoScanSubComponent
import com.senior.d_text.presentation.di.home.HomeSubComponent
import com.senior.d_text.presentation.di.messagehistory.MessageHistorySubComponent
import com.senior.d_text.presentation.di.messageService.MessageServiceSubComponent
import com.senior.d_text.presentation.di.notificationService.NotificationServiceSubComponent
import com.senior.d_text.presentation.di.notificationhistory.NotificationHistorySubComponent
import com.senior.d_text.presentation.di.setting.SettingSubComponent

interface Injector {
    fun createHomeSubComponent(): HomeSubComponent
    fun createMessageHistorySubComponent(): MessageHistorySubComponent
    fun createNotificationHistorySubComponent(): NotificationHistorySubComponent
    fun createMessageService(): MessageServiceSubComponent
    fun createNotificationService(): NotificationServiceSubComponent
    fun createSignInSubComponent(): SignInSubComponent
    fun createSignUpSubComponent(): SignUpSubComponent
    fun createAuthenticationSubComponent(): AuthenticationSubcomponent
    fun createSettingSubComponent(): SettingSubComponent
    fun createAutoScanSubComponent(): AutoScanSubComponent
}