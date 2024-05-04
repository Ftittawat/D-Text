package com.senior.d_text.presentation.setting.about

import android.app.Application
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import androidx.lifecycle.AndroidViewModel

class SettingAboutViewModel(application: Application) : AndroidViewModel(application) {

    val version: String = getVersionName()

    private fun getVersionName(): String {
        return try {
            val packageInfo: PackageInfo =
                getApplication<Application>().packageManager.getPackageInfo(
                    getApplication<Application>().packageName, 0
                )
            packageInfo.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            "N/A"
        }
    }

}