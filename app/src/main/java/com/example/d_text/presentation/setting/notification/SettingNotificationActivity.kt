package com.example.d_text.presentation.setting.notification

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.d_text.databinding.ActivitySettingNotificationBinding
import javax.inject.Inject

class SettingNotificationActivity : AppCompatActivity() {

    @Inject
    private lateinit var binding: ActivitySettingNotificationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        setupButton()
    }

    private fun setupButton() {
        binding.backButton.setOnClickListener {
            this.finish()
        }
    }

}