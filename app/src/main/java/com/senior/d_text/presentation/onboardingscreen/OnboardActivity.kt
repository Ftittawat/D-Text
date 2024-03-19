package com.senior.d_text.presentation.onboardingscreen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.senior.d_text.R
import com.senior.d_text.databinding.ActivityOnboardBinding
import com.senior.d_text.presentation.onboardingscreen.first.FirstOnboardFragment
import com.senior.d_text.presentation.onboardingscreen.second.SecondOnboardFragment
import com.senior.d_text.presentation.onboardingscreen.third.ThirdOnboardFragment
import com.senior.d_text.presentation.welcomescreen.WelcomeScreenActivity

class OnboardActivity: AppCompatActivity() {

    private lateinit var binding: ActivityOnboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentList = arrayListOf<Fragment>(
            FirstOnboardFragment(),
            SecondOnboardFragment(),
            ThirdOnboardFragment()
        )

        val adapter = OnboardAdapter(
            fragmentList,
            supportFragmentManager,
            lifecycle
        )

        binding.ViewPager.adapter = adapter
        binding.dotsIndicator.attachTo(binding.ViewPager)
    }

    override fun onResume() {
        super.onResume()
        setupButton()
    }

    private fun setupButton() {
        if (binding.ViewPager.currentItem == 2) {
            binding.nextButton.isInvisible = true
            binding.startButton.isVisible = true
        } else if (binding.ViewPager.currentItem < 2) {
            binding.startButton.isInvisible = true
            binding.nextButton.isVisible = true
        }
        binding.nextButton.setOnClickListener {
            binding.ViewPager.currentItem++
            if (binding.ViewPager.currentItem == 2) {
                binding.nextButton.isInvisible = true
                binding.startButton.isVisible = true
            }
            // Log.d("log", "setupButton: ${binding.ViewPager.currentItem}")
        }
        binding.startButton.setOnClickListener {
            intent = Intent(this, WelcomeScreenActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.animate_fade_enter, R.anim.animate_fade_exit)
            // setDefaultSetting()
            onBoardFinished()
        }
    }

    private fun onBoardFinished() {
        val sharePref = getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharePref.edit()
        editor.putBoolean("finish", true)
        editor.apply()
    }

    private fun setDefaultSetting() {
        "unsafe".saveNotification(true)
        "suspicious".saveNotification(true)
    }

    private fun String.saveNotification(state: Boolean) {
        val sharePref = getSharedPreferences("Setting_Notification", MODE_PRIVATE)
        val editor = sharePref.edit()
        // Log.d("log", "saveNotification: $state")
        editor.putBoolean(this, state)
        editor.apply()
    }
}