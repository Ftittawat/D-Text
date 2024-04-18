package com.senior.d_text.presentation.setting.autoscan

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.senior.d_text.R
import com.senior.d_text.databinding.ActivitySettingScanBinding
import com.senior.d_text.presentation.service.MessageService
import com.senior.d_text.presentation.service.NotificationService
import javax.inject.Inject

class SettingAutoScanActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingScanBinding
    private lateinit var vm: SettingAutoScanViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingScanBinding.inflate(layoutInflater)
        vm = ViewModelProvider(this)[SettingAutoScanViewModel::class.java]
        setContentView(binding.root)
        requestPermissions()
    }

    override fun onStart() {
        super.onStart()
        setupView()
    }

    override fun onResume() {
        super.onResume()
        setupButton()
    }

    private fun setupView() {
        binding.detectButton.isChecked = vm.loadAutoDetection(AUTO_DETECTION)
        binding.messageButton.isChecked = vm.loadAutoDetection(MESSAGE_DETECT)
        binding.notificationButton.isChecked = vm.loadAutoDetection(NOTIFICATION_DETECT)
    }

    private fun setupButton() {
        binding.backButton.setOnClickListener {
            onBackPressed()
        }
        binding.detectButton.setOnCheckedChangeListener { _, isChecked ->
            vm.saveAutoDetection(AUTO_DETECTION, isChecked)
//            if (isChecked) {
//                startMessageService()
//            } else {
//                stopMessageService()
//            }
        }
        binding.messageButton.setOnCheckedChangeListener { _, isChecked ->
            vm.saveAutoDetection(MESSAGE_DETECT, isChecked)
            messageService(isChecked)
        }
        binding.notificationButton.setOnCheckedChangeListener { _, isChecked ->
            vm.saveAutoDetection(NOTIFICATION_DETECT ,isChecked)
            notificationService(isChecked)
        }
    }

    private fun requestPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECEIVE_SMS), RECEIVE_SMS_REQUEST_CODE)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            requestCode -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.isNotEmpty() && grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                    // All permissions granted, proceed
                } else {
                    // Permissions not granted, handle accordingly (show a message, disable functionality, etc.)
                }
                return
            }
            else -> {
                // Handle other requestCode if any
            }
        }
    }

//    private fun String.saveAutoDetection(state: Boolean) {
//        val sharePref = getSharedPreferences("Setting_Detection", MODE_PRIVATE)
//        val editor = sharePref.edit()
//        // Log.d("log", "saveNotification: $state")
//        editor.putBoolean(this, state)
//        editor.apply()
//    }
//
//    private fun String.loadAutoDetection(): Boolean {
//        val sharePref = getSharedPreferences("Setting_Detection", MODE_PRIVATE)
//        // val state = sharePref.getBoolean(this, false)
//        // Log.d("log", "loadNotification:$this -> $state")
//        return sharePref.getBoolean(this, false)
//    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.animate_slide_in_left, R.anim.animate_slide_out_right)
    }

    private fun messageService(state: Boolean) {
        val serviceIntent = Intent(this, MessageService::class.java)
        if (state) {
            startService(serviceIntent)
            Log.d("logMessages", "startMessageService")
        } else {
            stopService(serviceIntent)
            Log.d("logMessages", "stopMessageService")
        }
    }

    private fun notificationService(state: Boolean) {
        val serviceIntent = Intent(this, NotificationService::class.java)
        if (state) {
            startService(serviceIntent)
            Log.d("logNoti", "startNotificationService")
        }
        else {
            stopService(serviceIntent)
            Log.d("logNoti", "stopNotificationService")
        }
    }

    private fun startMessageService() {
        val serviceIntent = Intent(this, MessageService::class.java)
        startService(serviceIntent)
        Log.d("logMessages", "startMessageService")
    }

    private fun stopMessageService() {
        val serviceIntent = Intent(this, MessageService::class.java)
        stopService(serviceIntent)
        Log.d("logMessages", "stopMessageService")
    }

    companion object {
        const val AUTO_DETECTION = "autodetect"
        const val MESSAGE_DETECT = "message_detect"
        const val NOTIFICATION_DETECT = "notification_detect"
        const val RECEIVE_SMS_REQUEST_CODE = 1006
    }

}