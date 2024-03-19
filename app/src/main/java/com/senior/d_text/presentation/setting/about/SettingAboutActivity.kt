package com.senior.d_text.presentation.setting.about

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.senior.d_text.R
import com.senior.d_text.databinding.ActivitySettingAboutBinding
import javax.inject.Inject

class SettingAboutActivity : AppCompatActivity() {

    @Inject
    private lateinit var binding: ActivitySettingAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingAboutBinding.inflate(layoutInflater)
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