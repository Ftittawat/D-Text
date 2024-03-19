package com.senior.d_text.presentation.home

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.senior.d_text.R
import com.senior.d_text.databinding.ActivityHomeBinding
import com.senior.d_text.presentation.core.HomeViewModelFactory
import com.senior.d_text.presentation.history.HistoryFragment
import com.senior.d_text.presentation.notification.NotificationFragment
import com.senior.d_text.presentation.setting.SettingActivity
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: HomeViewModelFactory
    private lateinit var vm: HomeViewModel
    private lateinit var binding: ActivityHomeBinding

    private val NOTIFICATION_PERMISSION_CODE = 1001

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

    override fun onStart() {
        super.onStart()
        searchWatcher { validateButton() }
    }

    override fun onResume() {
        super.onResume()
        setupButton()
        validateButton()
    }

    private fun setupView() {

    }

    private fun setupButton() {
//        binding.notificationIndicator.isVisible = true
        binding.activity.setOnClickListener {
            it.hideKeyboard()
            binding.searchBox.clearFocus()
            validateButton()
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
            overridePendingTransition(R.anim.animate_slide_in_right, R.anim.animate_slide_out_left)
        }
        binding.scanButton.setOnClickListener {
//            testNotification(applicationContext)
            val historyFragment = HistoryFragment()
            val bundle = Bundle()
            getSearchValue()
            bundle.putString("URL", vm.url.value.toString())
            historyFragment.arguments = bundle
            historyFragment.show(supportFragmentManager, "HistoryFragmentTag")
        }
        binding.scanButton.isEnabled = false
    }

    private fun searchWatcher(func: () -> Unit) {
        binding.searchBox.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                func()
            }
        })
    }

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

//    fun testNotification(context: Context) {
//        val channelId = "test_notification"
//        val title = "Are you looking for awesome Android article?"
//        val text = "Why don't you look at Sleeping For Less website. There's a lot of interest article about Android application development."
//        val notification = NotificationCompat.Builder(context, channelId).apply {
//            setSmallIcon(R.drawable.logo_blue)
//            setContentTitle(title)
//            setContentText(text)
//        }.build()
//        val id = 0
//        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//        manager.notify(id, notification)
//    }

    private fun checkVibratePermission(): Boolean {
        // Log.d("log", "checkVibratePermission: ")
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.VIBRATE
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestVibratePermission() {
        // Log.d("log", "requestVibratePermission: ")
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.VIBRATE),
            NOTIFICATION_PERMISSION_CODE
        )
    }

    // Handle the result of the permission request
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == NOTIFICATION_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted
                // Perform your tasks that may involve notifications here
            } else {
                // Permission denied
                // Handle the case where the user denies the permission
            }
        }
    }

    private fun getSearchValue() {
        vm.url.value = vm.validationUrl(binding.searchBox.text.toString())
    }

    private fun validateButton() {
        binding.scanButton.isEnabled = !binding.searchBox.text.toString().isEmpty()
    }

}