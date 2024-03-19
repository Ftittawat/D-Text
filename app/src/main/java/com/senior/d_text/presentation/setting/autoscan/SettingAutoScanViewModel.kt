package com.senior.d_text.presentation.setting.autoscan

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.AndroidViewModel

class SettingAutoScanViewModel(application: Application) : AndroidViewModel(application) {

    private val sharePref = application.getSharedPreferences("Setting_Detection", AppCompatActivity.MODE_PRIVATE)

    fun saveAutoDetection(tag: String, state: Boolean) {
        val editor = sharePref.edit()
        // Log.d("log", "saveNotification: $state")
        editor.putBoolean(tag, state)
        editor.apply()
    }

    fun loadAutoDetection(tag: String): Boolean {
        // val state = sharePref.getBoolean(this, false)
        // Log.d("log", "loadNotification:$this -> $state")
        return sharePref.getBoolean(tag, false)
    }

    companion object {
        const val AUTO_DETECTION = "autodetect"
        const val MESSAGE_DETECT = "message_detect"
        const val NOTIFICATION_DETECT = "notification_detect"
    }
}