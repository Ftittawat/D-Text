package com.example.d_text.presentation.setting.policy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.d_text.databinding.ActivitySettingPolicyBinding
import javax.inject.Inject

class SettingPolicyActivity : AppCompatActivity() {

    @Inject
    private lateinit var binding: ActivitySettingPolicyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingPolicyBinding.inflate(layoutInflater)
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