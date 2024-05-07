package com.senior.d_text.presentation.setting

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.senior.d_text.data.model.authentication.UserToken
import com.senior.d_text.domain.usecase.SignOutUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingViewModel @Inject constructor (
    private val application: Application,
    private val signOutUseCase: SignOutUseCase
) : AndroidViewModel(application) {

    private val sharePref = application.getSharedPreferences("account", Context.MODE_PRIVATE)

    val version: String = getVersionName()
    private val userToken = loadUserToken()

    fun signOut() = viewModelScope.launch {
        signOutUseCase.execute(userToken.id.toString())
        removeUserToken()
//        Log.d("auth", "signOut: ${loadUserToken()}")
    }

    private fun removeUserToken() {
        val editor = sharePref.edit()
        editor.putString("token_id", null)
        editor.putString("access_token", null)
        editor.putString("refresh_token", null)
        editor.apply()
    }

    private fun loadUserToken(): UserToken {
        val tokenId = sharePref.getString("token_id", "")
        val accessToken = sharePref.getString("access_token", "")
        val refreshToken = sharePref.getString("refresh_token", "")
        Log.d("auth", "loadUserToken: ${tokenId.toString()} -> ${accessToken.toString()}")
        return UserToken(tokenId, accessToken, refreshToken)
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