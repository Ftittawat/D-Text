package com.senior.d_text.presentation.setting

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.AndroidViewModel
import com.senior.d_text.domain.usecase.SignOutUseCase
import javax.inject.Inject

class SettingViewModel @Inject constructor (
    private val application: Application,
    private val signOutUseCase: SignOutUseCase
) : AndroidViewModel(application) {

    private val sharePref = application.getSharedPreferences("account", Context.MODE_PRIVATE)

    val version: String = getVersionName()

     fun loadToken(): String? {
        return sharePref.getString("token", null)
    }

    fun removeToken() {
        val editor = sharePref.edit()
        editor.putString("token", null)
        editor.apply()
    }

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