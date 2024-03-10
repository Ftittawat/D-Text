package com.example.d_text.presentation.home

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.d_text.databinding.ActivityHomeBinding
import com.example.d_text.presentation.core.HomeViewModelFactory
import com.example.d_text.presentation.di.Injector
import com.example.d_text.presentation.history.HistoryFragment
import com.example.d_text.presentation.notification.NotificationFragment
import com.example.d_text.presentation.onboardingscreen.first.FirstOnboardFragment
import com.example.d_text.presentation.setting.SettingActivity
import com.example.d_text.presentation.setting.SettingViewModel
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: HomeViewModelFactory
    private lateinit var vm: HomeViewModel
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        (application as Injector).createHomeSubComponent()
//            .inject(this)
        vm = ViewModelProvider(this)[HomeViewModel::class.java]
//        Handler().postDelayed({
//            val fragmentManager = supportFragmentManager
//            val fragmentTransaction = fragmentManager.beginTransaction()
//            fragmentTransaction.replace(binding.fragmentContainer.id, FirstOnboardFragment.newInstance())
//            fragmentTransaction.commit()
//        }, 2000)
    }

    override fun onResume() {
        super.onResume()
        setupButton()
    }

    private fun setupButton() {
//        binding.notificationIndicator.isVisible = true
        binding.activity.setOnClickListener {
            it.hideKeyboard()
        }
        binding.notificationButton.setOnClickListener {
            val notificationFragment = NotificationFragment()
//            val bundle = Bundle()
//            binding.notificationIndicator.isGone = true
//            bundle.putString("test", "NotificationFragmentTest")
//            bundle.putInt("test2", 500)
//            notificationFragment.arguments = bundle
            notificationFragment.show(supportFragmentManager, "NotificationFragmentTag")
        }
        binding.settingButton.setOnClickListener {
            intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }
        binding.scanButton.setOnClickListener {
            val historyFragment = HistoryFragment()
            historyFragment.show(supportFragmentManager, "HistoryFragmentTag")
        }
    }

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}