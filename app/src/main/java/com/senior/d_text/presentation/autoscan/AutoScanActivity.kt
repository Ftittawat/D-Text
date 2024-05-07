package com.senior.d_text.presentation.autoscan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.senior.d_text.R
import com.senior.d_text.databinding.ActivityAutoScanBinding
import com.senior.d_text.presentation.core.AutoScanViewModelFactory
import com.senior.d_text.presentation.di.Injector
import javax.inject.Inject

class AutoScanActivity: AppCompatActivity() {

    @Inject
    lateinit var factory: AutoScanViewModelFactory
    private lateinit var binding: ActivityAutoScanBinding
    private lateinit var vm: AutoScanViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAutoScanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        (application as Injector).createAutoScanSubComponent()
            .inject(this)
        vm = ViewModelProvider(this, factory)[AutoScanViewModel::class.java]
    }

    override fun onStart() {
        super.onStart()
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
        overridePendingTransition(R.anim.animate_slide_in_right, R.anim.animate_slide_out_left)
    }
}