package com.example.d_text.presentation.splashscreen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.d_text.databinding.ActivitySplashScreenBinding
import com.example.d_text.presentation.home.HomeActivity
import com.example.d_text.presentation.onboardingscreen.OnboardActivity
import com.example.d_text.presentation.welcomescreen.WelcomeScreenActivity

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navigation()
    }

    private fun navigation() {
        // Log.d("log", "${onBoardFinished()}")
        if (onBoardFinished()) {
            Handler().postDelayed({
                intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                finish()
            }, 2000)
        } else {
            Handler().postDelayed({
                intent = Intent(this, OnboardActivity::class.java)
                startActivity(intent)
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                finish()
            }, 2000)
        }
    }

    private fun onBoardFinished(): Boolean {
        val sharePref = getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        // val finish: Boolean = sharePref.getBoolean("finish", false)
        return sharePref.getBoolean("finish", false)
    }
}