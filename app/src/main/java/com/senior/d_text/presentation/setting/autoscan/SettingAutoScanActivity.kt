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
import com.senior.d_text.presentation.home.HomeActivity
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
            if (isChecked && vm.loadAutoDetection(MESSAGE_DETECT)) {
                messageService(true)
                // Log.d("logService", "messageService True")
            }
            if (isChecked && vm.loadAutoDetection(NOTIFICATION_DETECT)) {
                notificationService(true)
                // Log.d("logService", "notificationService True")
            }
            else {
                messageService(false)
                notificationService(false)
                // Log.d("logService", "messageService False")
                // Log.d("logService", "notificationService False")
            }
        }
        binding.messageButton.setOnCheckedChangeListener { _, isChecked ->
            vm.saveAutoDetection(MESSAGE_DETECT, isChecked)
            if (isChecked && vm.loadAutoDetection(AUTO_DETECTION)) {
                messageService(true)
                // Log.d("logService", "messageService True")
            } else {
                messageService(false)
                // Log.d("logService", "messageService False")
            }
        }
        binding.notificationButton.setOnCheckedChangeListener { _, isChecked ->
            vm.saveAutoDetection(NOTIFICATION_DETECT ,isChecked)
//            notificationService(isChecked)
            if (isChecked && vm.loadAutoDetection(AUTO_DETECTION)) {
                notificationService(true)
                // Log.d("logService", "notificationService True")
            } else {
                notificationService(false)
                // Log.d("logService", "notificationService False")
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

    private fun requestPermissions() {

        val permissions = arrayOf(
            Manifest.permission.RECEIVE_SMS to RECEIVE_SMS_REQUEST_CODE,
            Manifest.permission.READ_SMS to READ_SMS_REQUEST_CODE
        )

        permissions.forEach { (permission, requestCode) ->
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(permission), requestCode)
            }
        }
    }

    private fun checkPermissions(permission: Int): Boolean {
        if (permission == RECEIVE_SMS_REQUEST_CODE) {
            return ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) == PackageManager.PERMISSION_GRANTED
        }
        if (permission == READ_SMS_REQUEST_CODE) {
            return ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED
        }
        return false
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            requestCode -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.isNotEmpty() && grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
//                    if (requestCode == RECEIVE_SMS_REQUEST_CODE) {
//                        messageService(true)
//                        binding.messageButton.isChecked = true
//                        vm.saveAutoDetection(MESSAGE_DETECT, true)
//                    }
                } else {
                    // Permissions not granted, handle accordingly (show a message, disable functionality, etc.)
//                    if (requestCode == RECEIVE_SMS_REQUEST_CODE) {
//                        binding.messageButton.isChecked = false
//                        vm.saveAutoDetection(MESSAGE_DETECT, false)
//                    }
                }
                return
            }
            else -> {
                // Handle other requestCode if any
            }
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
        const val NOTIFICATION_PERMISSION_CODE = 1001
        const val INTERNET_REQUEST_CODE = 1002
        const val POST_NOTIFICATIONS_REQUEST_CODE = 1003
        const val VIBRATE_REQUEST_CODE = 1004
        const val FOREGROUND_SERVICE_REQUEST_CODE = 1005
        const val RECEIVE_SMS_REQUEST_CODE = 1006
        const val READ_SMS_REQUEST_CODE = 1007
        const val BIND_NOTIFICATION_LISTENER_SERVICE = 1008
    }

}