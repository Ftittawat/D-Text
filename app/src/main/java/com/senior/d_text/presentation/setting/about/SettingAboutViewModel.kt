package com.senior.d_text.presentation.setting.about

import android.app.Application
import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.AndroidViewModel
import com.senior.d_text.BuildConfig
import com.senior.d_text.data.model.authentication.UserToken
import com.senior.d_text.data.model.setting.AutoScanSetting
import com.senior.d_text.data.model.setting.NotificationSetting
import com.senior.d_text.presentation.service.NotificationService

class SettingAboutViewModel(application: Application) : AndroidViewModel(application) {

    private val sharePrefAccount = application.getSharedPreferences("account", Context.MODE_PRIVATE)
    private val sharePrefNotification = application.getSharedPreferences("Setting_Notification", Context.MODE_PRIVATE)
    private val sharePrefAutoScan = application.getSharedPreferences("Setting_Detection", Context.MODE_PRIVATE)

    val version: String = getVersionName()
    val sdkVersion: String = getSDKVersion()
    //val codeVersion: String = getCodeVersion()

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

    private fun getSDKVersion(): String {
        return Build.VERSION.SDK_INT.toString()
    }

    private fun getCodeVersion(): String {
        return BuildConfig.VERSION_CODE.toString()
    }

     fun loadUserToken(): UserToken {
        val tokenId = sharePrefAccount.getString("token_id", "")
        val accessToken = sharePrefAccount.getString("access_token", "")
        val refreshToken = sharePrefAccount.getString("refresh_token", "")
        return UserToken(tokenId, accessToken, refreshToken)
    }

     fun loadNotificationSetting(): NotificationSetting {
        val notification = sharePrefNotification.getBoolean(NOTIFICATION, false)
        val safe = sharePrefNotification.getBoolean(SAFE, false)
        val suspicious = sharePrefNotification.getBoolean(SUSPICIOUS, false)
        val unsafe = sharePrefNotification.getBoolean(UNSAFE, false)
        val noInformation = sharePrefNotification.getBoolean(NO_INFORMATION, false)
        return NotificationSetting(notification, unsafe, suspicious, safe, noInformation)
    }

    fun loadAutoScanSetting(): AutoScanSetting {
        val autoDetect = sharePrefAutoScan.getBoolean(AUTO_DETECTION, false)
        val message = sharePrefAutoScan.getBoolean(MESSAGE_DETECT, false)
        val notification = sharePrefAutoScan.getBoolean(NOTIFICATION_DETECT, false)
        return AutoScanSetting(autoDetect, message, notification)
    }

    companion object {
        const val AUTO_DETECTION = "autodetect"
        const val MESSAGE_DETECT = "message_detect"
        const val NOTIFICATION_DETECT = "notification_detect"
        const val NOTIFICATION = "notification"
        const val UNSAFE = "unsafe"
        const val SUSPICIOUS = "suspicious"
        const val SAFE = "safe"
        const val NO_INFORMATION = "noinformation"
    }

}