package com.senior.d_text.presentation.setting.autoscan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.senior.d_text.R
import com.senior.d_text.databinding.ActivitySettingScanBinding
import javax.inject.Inject

class SettingAutoScanActivity : AppCompatActivity() {

    @Inject
    private lateinit var binding: ActivitySettingScanBinding
    private lateinit var vm: SettingAutoScanViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingScanBinding.inflate(layoutInflater)
        vm = ViewModelProvider(this)[SettingAutoScanViewModel::class.java]
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        setupButton()
    }

    private fun setupButton() {
        binding.backButton.setOnClickListener {
            onBackPressed()
        }

        binding.detectButton.isChecked = vm.loadAutoDetection(AUTO_DETECTION)
        binding.messageButton.isChecked = vm.loadAutoDetection(MESSAGE_DETECT)
        binding.notificationButton.isChecked = vm.loadAutoDetection(NOTIFICATION_DETECT)

        binding.detectButton.setOnCheckedChangeListener { _, isChecked ->
            vm.saveAutoDetection(AUTO_DETECTION, isChecked)
        }
        binding.messageButton.setOnCheckedChangeListener { _, isChecked ->
            vm.saveAutoDetection(MESSAGE_DETECT, isChecked)
        }
        binding.notificationButton.setOnCheckedChangeListener { _, isChecked ->
            vm.saveAutoDetection(NOTIFICATION_DETECT ,isChecked)
        }
    }

    private fun String.saveAutoDetection(state: Boolean) {
        val sharePref = getSharedPreferences("Setting_Detection", MODE_PRIVATE)
        val editor = sharePref.edit()
        // Log.d("log", "saveNotification: $state")
        editor.putBoolean(this, state)
        editor.apply()
    }

    private fun String.loadAutoDetection(): Boolean {
        val sharePref = getSharedPreferences("Setting_Detection", MODE_PRIVATE)
        // val state = sharePref.getBoolean(this, false)
        // Log.d("log", "loadNotification:$this -> $state")
        return sharePref.getBoolean(this, false)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.animate_slide_in_left, R.anim.animate_slide_out_right)
    }

    companion object {
        const val AUTO_DETECTION = "autodetect"
        const val MESSAGE_DETECT = "message_detect"
        const val NOTIFICATION_DETECT = "notification_detect"
    }

}