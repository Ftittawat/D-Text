package com.senior.d_text.presentation.di.autoscan

import com.senior.d_text.presentation.autoscan.AutoScanActivity
import dagger.Subcomponent

@AutoScanScope
@Subcomponent(modules = [AutoScanModule::class])
interface AutoScanSubComponent {
    fun inject(autoScanActivity: AutoScanActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create():AutoScanSubComponent
    }
}