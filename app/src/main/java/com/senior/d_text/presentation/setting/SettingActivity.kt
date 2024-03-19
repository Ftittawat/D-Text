package com.senior.d_text.presentation.setting

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.window.OnBackInvokedDispatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.senior.d_text.R
import com.senior.d_text.databinding.ActivitySettingBinding
import com.senior.d_text.presentation.setting.about.SettingAboutActivity
import com.senior.d_text.presentation.setting.autoscan.SettingAutoScanActivity
import com.senior.d_text.presentation.setting.notification.SettingNotificationActivity
import com.senior.d_text.presentation.setting.policy.SettingPolicyActivity
import com.senior.d_text.presentation.welcomescreen.WelcomeScreenActivity
import javax.inject.Inject

class SettingActivity : AppCompatActivity() {

    @Inject
    private lateinit var vm: SettingViewModel
    private lateinit var binding: ActivitySettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        vm = ViewModelProvider(this)[SettingViewModel::class.java]
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
        binding.version.text = vm.version
    }

    private fun setupButton() {
        binding.backButton.setOnClickListener {
            onBackPressed()
        }
        binding.settingNotification.setOnClickListener {
            intent = Intent(this, SettingNotificationActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.animate_slide_in_right, R.anim.animate_slide_out_left)
//            if (Build.VERSION.SDK_INT >= 34) {
//                this@SettingActivity.overrideActivityTransition(
//                    OVERRIDE_TRANSITION_OPEN,
//                    R.anim.animate_slide_in_right,
//                    R.anim.animate_slide_out_left
//                )
//            } else {
//                overridePendingTransition(R.anim.animate_slide_in_right, R.anim.animate_slide_out_left)
//            }
        }
        binding.settingScan.setOnClickListener {
            intent = Intent(this, SettingAutoScanActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.animate_slide_in_right, R.anim.animate_slide_out_left)
        }
        binding.settingAbout.setOnClickListener {
            intent = Intent(this, SettingAboutActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.animate_slide_in_right, R.anim.animate_slide_out_left)
        }
        binding.settingPolicy.setOnClickListener {
            intent = Intent(this, SettingPolicyActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.animate_slide_in_right, R.anim.animate_slide_out_left)
        }
        binding.logoutButton.setOnClickListener {
            intent = Intent(this, WelcomeScreenActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.animate_slide_in_left, R.anim.animate_slide_out_right)
    }
}