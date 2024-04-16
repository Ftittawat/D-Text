package com.senior.d_text.presentation.home

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.senior.d_text.R
import com.senior.d_text.data.model.history.History
import com.senior.d_text.databinding.ActivityHomeBinding
import com.senior.d_text.presentation.core.HomeViewModelFactory
import com.senior.d_text.presentation.core.OnItemClickListener
import com.senior.d_text.presentation.di.Injector
import com.senior.d_text.presentation.history.HistoryFragment
import com.senior.d_text.presentation.notification.NotificationFragment
import com.senior.d_text.presentation.setting.SettingActivity
import javax.inject.Inject
import javax.inject.Singleton

class HomeActivity() : AppCompatActivity() {

    @Inject
    lateinit var factory: HomeViewModelFactory
    private lateinit var vm: HomeViewModel
    private lateinit var binding: ActivityHomeBinding
    private  lateinit var adapter: HistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        (application as Injector).createHomeSubComponent()
            .inject(this)
        vm = ViewModelProvider(this, factory)[HomeViewModel::class.java]
        requestPermissions()
        initRecyclerView()

//        Handler().postDelayed({
//            val fragmentManager = supportFragmentManager
//            val fragmentTransaction = fragmentManager.beginTransaction()
//            fragmentTransaction.replace(binding.fragmentContainer.id, FirstOnboardFragment.newInstance())
//            fragmentTransaction.commit()
//        }, 2000)
    }

    override fun onStart() {
        super.onStart()
        setupView()
        searchWatcher { validateButton() }
    }

    override fun onResume() {
        super.onResume()
        setupButton()
        validateButton()
    }

    private fun setupView() {
        Log.d("sms", "messages: init")
        vm.startListeningForMessages()
        vm.messages.observe(this) {
            Log.d("sms", "messages: ${vm.messages.value}")
            Log.d("sms", "url: ${vm.messagesUrl.value}")
        }
        vm.messagesUrl.observe(this) {
            // Log.d("sms", "url: ${vm.messagesUrl.value}")
        }
    }

    private fun setupButton() {
        // binding.notificationIndicator.isVisible = true
        binding.activity.setOnClickListener {
            it.hideKeyboard()
            binding.searchBox.clearFocus()
            validateButton()
        }
        binding.titleContainer.setOnClickListener {
            Log.d("log", "click")
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
            val testData = History( 0, vm.url.value.toString(), "safe", "web", "2024-04-16")
            vm.saveHistory(testData)
            historyFragment.arguments = bundle
            historyFragment.show(supportFragmentManager, "HistoryFragmentTag")
        }
        binding.historyDelete.setOnClickListener {
            vm.deleteAllHistory()
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

    private fun requestPermissions() {
        val permissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                arrayOf(
                    Manifest.permission.INTERNET to INTERNET_REQUEST_CODE,
                    Manifest.permission.POST_NOTIFICATIONS to POST_NOTIFICATIONS_REQUEST_CODE,
                    Manifest.permission.VIBRATE to VIBRATE_REQUEST_CODE,
                    Manifest.permission.FOREGROUND_SERVICE to FOREGROUND_SERVICE_REQUEST_CODE,
                    Manifest.permission.RECEIVE_SMS to RECEIVE_SMS_REQUEST_CODE,
                    Manifest.permission.READ_SMS to READ_SMS_REQUEST_CODE
                )
            } else {
                arrayOf(
                    Manifest.permission.INTERNET to INTERNET_REQUEST_CODE,
                    Manifest.permission.VIBRATE to VIBRATE_REQUEST_CODE,
                    Manifest.permission.FOREGROUND_SERVICE to FOREGROUND_SERVICE_REQUEST_CODE,
                    Manifest.permission.RECEIVE_SMS to RECEIVE_SMS_REQUEST_CODE,
                    Manifest.permission.READ_SMS to READ_SMS_REQUEST_CODE
                )
            }
        } else  {
            arrayOf(
                Manifest.permission.INTERNET to INTERNET_REQUEST_CODE,
                Manifest.permission.VIBRATE to VIBRATE_REQUEST_CODE,
                Manifest.permission.RECEIVE_SMS to RECEIVE_SMS_REQUEST_CODE,
                Manifest.permission.READ_SMS to READ_SMS_REQUEST_CODE
            )
        }

//        permissions.forEach { (permission, requestCode) ->
//            ActivityCompat.requestPermissions(this, arrayOf(permission), requestCode)
//        }

        permissions.forEach { (permission, requestCode) ->
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(permission), requestCode)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            requestCode -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.isNotEmpty() && grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                    Log.d("log", "$requestCode : PERMISSION_GRANTED")
                } else {
                    Log.d("log", "$requestCode : PERMISSION_DENIED")
                }
                return
            }
            else -> {
                // Handle other requestCode if any
            }
        }
    }

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
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//
//        if (requestCode == NOTIFICATION_PERMISSION_CODE) {
//            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                // Permission granted
//                // Perform your tasks that may involve notifications here
//            } else {
//                // Permission denied
//                // Handle the case where the user denies the permission
//            }
//        }
//    }

    private fun initRecyclerView() {
        binding.historyRecyclerview.layoutManager = LinearLayoutManager(this)
        adapter = HistoryAdapter()
        binding.historyRecyclerview.adapter = adapter
        displayHistory()
        adapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                //Toast.makeText(applicationContext, adapter.historyList[position].url, Toast.LENGTH_SHORT).show()
                val historyFragment = HistoryFragment()
                val bundle = Bundle()
                getSearchValue()
                bundle.putString("URL", adapter.historyList[position].url)
                bundle.putString("RISK_LEVEL", adapter.historyList[position].risk_level)
                bundle.putString("TYPE", adapter.historyList[position].type)
                bundle.putString("DATE_TIME", adapter.historyList[position].date_time)
                historyFragment.arguments = bundle
                historyFragment.show(supportFragmentManager, "HistoryFragmentTag")
            }
        })
    }

    private fun displayHistory() {
        val responseData = vm.getHistory()
        responseData.observe(this) {
            if (it != null) {
                adapter.setList(it)
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(applicationContext, "No Data", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun getSearchValue() {
        vm.url.value = vm.validationUrl(binding.searchBox.text.toString())
    }

    private fun validateButton() {
        binding.scanButton.isEnabled = !binding.searchBox.text.toString().isEmpty()
    }

    companion object {
        const val NOTIFICATION_PERMISSION_CODE = 1001
        const val INTERNET_REQUEST_CODE = 1002
        const val POST_NOTIFICATIONS_REQUEST_CODE = 1003
        const val VIBRATE_REQUEST_CODE = 1004
        const val FOREGROUND_SERVICE_REQUEST_CODE = 1005
        const val RECEIVE_SMS_REQUEST_CODE = 1006
        const val READ_SMS_REQUEST_CODE = 1007
    }

}