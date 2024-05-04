package com.senior.d_text.presentation.setting.about

import android.app.Notification
import android.os.Bundle
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.senior.d_text.R
import com.senior.d_text.databinding.ActivitySettingAboutBinding
import javax.inject.Inject

class SettingAboutActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingAboutBinding
    private lateinit var vm: SettingAboutViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        vm = ViewModelProvider(this)[SettingAboutViewModel::class.java]
    }

    override fun onStart() {
        super.onStart()
        setupView()
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

    private fun setupView() {
        binding.version.text = vm.version
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.animate_slide_in_left, R.anim.animate_slide_out_right)
    }

}

//class MyNotificationListenerService : NotificationListenerService() {
//
//    override fun onListenerConnected() {
//        super.onListenerConnected()
//        Log.d("Notification", "Connected to notification service")
//    }
//
//    override fun onNotificationPosted(sb: StatusBarNotification?) {
//        super.onNotificationPosted(sb)
//        Log.d("Notification", "Notification posted: ${sb?.notification?.tickerText}")
//    }
//
//    override fun onNotificationRemoved(sb: StatusBarNotification?) {
//        super.onNotificationRemoved(sb)
//        Log.d("Notification", "Notification removed: ${sb?.notification?.tickerText}")
//    }
//}