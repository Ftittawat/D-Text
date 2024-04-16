package com.senior.d_text.presentation.setting.policy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.senior.d_text.R
import com.senior.d_text.databinding.ActivitySettingPolicyBinding
import javax.inject.Inject

class SettingPolicyActivity : AppCompatActivity() {

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
            onBackPressed()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.animate_slide_in_left, R.anim.animate_slide_out_right)
    }

}