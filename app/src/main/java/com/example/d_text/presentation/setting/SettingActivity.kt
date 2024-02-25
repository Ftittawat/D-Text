package com.example.d_text.presentation.setting

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.d_text.R
import com.example.d_text.databinding.ActivitySettingBinding
import com.example.d_text.presentation.home.HomeViewModel
import com.example.d_text.presentation.setting.about.SettingAboutActivity
import com.example.d_text.presentation.setting.autoscan.SettingAutoScanActivity
import com.example.d_text.presentation.setting.notification.SettingNotificationActivity
import com.example.d_text.presentation.setting.policy.SettingPolicyActivity
import javax.inject.Inject

class SettingActivity : AppCompatActivity() {

    @Inject
//    lateinit var factory: SettingViewModelFactory
    lateinit var factory: ViewModelProvider.Factory
    private lateinit var viewModel: SettingViewModel
    private lateinit var binding: ActivitySettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        setupButton()
    }

    private fun setupButton() {
        binding.backButton.setOnClickListener {
//            onBackPressed()
            this.finish()
        }
        binding.settingNotification.setOnClickListener {
            intent = Intent(this, SettingNotificationActivity::class.java)
            startActivity(intent)
//            if (Build.VERSION.SDK_INT >= 34) {
//                this@SettingActivity.overrideActivityTransition(
//                    OVERRIDE_TRANSITION_OPEN,
//                    R.anim.animate_slide_in_left,
//                    R.anim.animate_slide_out_right
//                )
//            }
        }
        binding.settingScan.setOnClickListener {
            intent = Intent(this, SettingAutoScanActivity::class.java)
            startActivity(intent)
        }
        binding.settingAbout.setOnClickListener {
            intent = Intent(this, SettingAboutActivity::class.java)
            startActivity(intent)
        }
        binding.settingPolicy.setOnClickListener {
            intent = Intent(this, SettingPolicyActivity::class.java)
            startActivity(intent)
        }
    }
}