package com.senior.d_text.presentation.setting.notification

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.AndroidViewModel

class SettingNotificationViewModel(application: Application) : AndroidViewModel(application) {

    private val sharePref = application.getSharedPreferences("Setting_Notification", AppCompatActivity.MODE_PRIVATE)

    fun saveNotification(tag: String, state: Boolean) {
        val editor = sharePref.edit()
        // Log.d("log", "saveNotification: $state")
        editor.putBoolean(tag, state)
        editor.apply()
    }

    fun loadNotification(tag: String): Boolean {
        // val state = sharePref.getBoolean(this, false)
        // Log.d("log", "loadNotification:$this -> $state")
        return sharePref.getBoolean(tag, false)
    }

    companion object {
        const val NOTIFICATION = "notification"
        const val UNSAFE = "unsafe"
        const val SUSPICIOUS = "suspicious"
        const val SAFE = "safe"
        const val NO_INFORMATION = "no_information"
    }
}