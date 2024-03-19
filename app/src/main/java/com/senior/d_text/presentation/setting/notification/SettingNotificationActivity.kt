package com.senior.d_text.presentation.setting.notification

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.senior.d_text.R
import com.senior.d_text.databinding.ActivitySettingNotificationBinding
import javax.inject.Inject

class SettingNotificationActivity : AppCompatActivity() {

    @Inject
    private lateinit var binding: ActivitySettingNotificationBinding
    private lateinit var vm: SettingNotificationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingNotificationBinding.inflate(layoutInflater)
        vm = ViewModelProvider(this)[SettingNotificationViewModel::class.java]
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        setupView()
    }

    override fun onResume() {
        super.onResume()
        setupButton()
    }

    private fun setupButton() {
        binding.backButton.setOnClickListener {
            onBackPressed()
        }

        binding.notificationButton.isChecked = vm.loadNotification(NOTIFICATION)
        binding.unsafeButton.isChecked = vm.loadNotification(UNSAFE)
        binding.suspiciousButton.isChecked = vm.loadNotification(SUSPICIOUS)
        binding.safeButton.isChecked = vm.loadNotification(SAFE)
        binding.noInformationButton.isChecked = vm.loadNotification(NO_INFORMATION)

        binding.notificationButton.setOnCheckedChangeListener { _, isChecked ->
            // Log.d("log", "setupButton: $isChecked")
            vm.saveNotification(NOTIFICATION, isChecked)
        }
        binding.unsafeButton.setOnCheckedChangeListener { _, isChecked ->
            vm.saveNotification(UNSAFE, isChecked)
        }
        binding.suspiciousButton.setOnCheckedChangeListener { _, isChecked ->
            vm.saveNotification(SUSPICIOUS, isChecked)
        }
        binding.safeButton.setOnCheckedChangeListener { _, isChecked ->
            vm.saveNotification(SAFE, isChecked)
        }
        binding.noInformationButton.setOnCheckedChangeListener { _, isChecked ->
            vm.saveNotification(NO_INFORMATION, isChecked)
        }
    }

    private fun setupView() {

    }

    private fun String.saveNotification(state: Boolean) {
        val sharePref = getSharedPreferences("Setting_Notification", MODE_PRIVATE)
        val editor = sharePref.edit()
        // Log.d("log", "saveNotification: $state")
        editor.putBoolean(this, state)
        editor.apply()
    }

    private fun String.loadNotification(): Boolean {
        val sharePref = getSharedPreferences("Setting_Notification", MODE_PRIVATE)
        // val state = sharePref.getBoolean(this, false)
        // Log.d("log", "loadNotification:$this -> $state")
        return sharePref.getBoolean(this, false)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.animate_slide_in_left, R.anim.animate_slide_out_right)
    }

    companion object {
        const val NOTIFICATION = "notification"
        const val UNSAFE = "unsafe"
        const val SUSPICIOUS = "suspicious"
        const val SAFE = "safe"
        const val NO_INFORMATION = "no_information"
    }

}