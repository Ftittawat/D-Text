package com.senior.d_text.presentation.di.setting

import com.senior.d_text.presentation.setting.SettingActivity
import dagger.Subcomponent

@SettingScope
@Subcomponent(modules = [SettingModule::class])
interface SettingSubComponent {
    fun inject(settingActivity: SettingActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create(): SettingSubComponent
    }
}