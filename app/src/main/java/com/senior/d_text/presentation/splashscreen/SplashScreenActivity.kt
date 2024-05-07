package com.senior.d_text.presentation.splashscreen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.senior.d_text.R
import com.senior.d_text.data.model.authentication.UserToken
import com.senior.d_text.databinding.ActivitySplashScreenBinding
import com.senior.d_text.presentation.authentication.AuthenticationActivity
import com.senior.d_text.presentation.home.HomeActivity
import com.senior.d_text.presentation.onboardingscreen.OnboardActivity
import com.senior.d_text.presentation.welcomescreen.WelcomeScreenActivity

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    private lateinit var userToken: UserToken

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userToken = loadUserToken()
        navigation()
    }

    private fun navigation() {
        // Log.d("log", "${onBoardFinished()}")
        if (onBoardFinished()) {
            if (userToken.access_token.isNullOrEmpty()) {
                Handler().postDelayed({
                    intent = Intent(this, WelcomeScreenActivity::class.java)
                    startActivity(intent)
                    overridePendingTransition(R.anim.animate_fade_enter, R.anim.animate_fade_exit)
                    finish()
                }, 2000)
            }
            else {
                Handler().postDelayed({
                    intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    overridePendingTransition(R.anim.animate_fade_enter, R.anim.animate_fade_exit)
                    finish()
                }, 2000)
            }
        } else {
            Handler().postDelayed({
                intent = Intent(this, OnboardActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.animate_fade_enter, R.anim.animate_fade_exit)
                finish()
            }, 2000)
        }
    }

    private fun onBoardFinished(): Boolean {
        val sharePref = getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        // val finish: Boolean = sharePref.getBoolean("finish", false)
        return sharePref.getBoolean("finish", false)
    }

    private fun loadToken(): String? {
        val sharePref = getSharedPreferences("account", Context.MODE_PRIVATE)
        return sharePref.getString("token", null)
    }

    private fun loadUserToken(): UserToken {
        val sharePref = application.getSharedPreferences("account", Context.MODE_PRIVATE)
        val tokenId = sharePref.getString("token_id", "")
        val accessToken = sharePref.getString("access_token", "")
        val refreshToken = sharePref.getString("refresh_token", "")
        return UserToken(tokenId, accessToken, refreshToken)
    }
}