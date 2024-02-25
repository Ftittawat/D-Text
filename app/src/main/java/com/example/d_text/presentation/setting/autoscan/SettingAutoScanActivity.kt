package com.example.d_text.presentation.setting.autoscan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.d_text.databinding.ActivitySettingScanBinding
import javax.inject.Inject

class SettingAutoScanActivity : AppCompatActivity() {

    @Inject
    private lateinit var binding: ActivitySettingScanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingScanBinding.inflate(layoutInflater)
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