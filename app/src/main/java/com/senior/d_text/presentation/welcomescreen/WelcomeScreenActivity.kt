package com.senior.d_text.presentation.welcomescreen

import android.accessibilityservice.AccessibilityServiceInfo
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.accessibility.AccessibilityManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.senior.d_text.R
import com.senior.d_text.databinding.ActivityWelcomeBinding
import com.senior.d_text.presentation.util.CustomDialogFragment
import com.senior.d_text.presentation.authentication.AuthenticationActivity
import com.senior.d_text.presentation.home.HomeActivity

class WelcomeScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
        binding.signInButton.setOnClickListener {
            intent = Intent(this, AuthenticationActivity::class.java)
            intent.putExtra("sign_in", true)
            intent.putExtra("guest", false)
            // intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
            startActivity(intent)
            overridePendingTransition(R.anim.animate_fade_enter, R.anim.animate_fade_exit)
        }

        binding.signUpButton.setOnClickListener {
            intent = Intent(this, AuthenticationActivity::class.java)
            intent.putExtra("sign_in", false)
            intent.putExtra("guest", false)
            // intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
            startActivity(intent)
            overridePendingTransition(R.anim.animate_fade_enter, R.anim.animate_fade_exit)
        }

        binding.guestMode.setOnClickListener {
            intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("guest", true)
            startActivity(intent)
            guestMode(true)
            overridePendingTransition(R.anim.animate_fade_enter, R.anim.animate_fade_exit)
        }
    }

    private fun setupView() {
        if (Build.VERSION.SDK_INT >= 34) {
            // showDialog(this)
            // val warningVersionFragment = VersionDialogFragment()
            // warningVersionFragment.show(supportFragmentManager, "WarningVersionFragment")
            val customDialogFragment = CustomDialogFragment()
            customDialogFragment.type = 2
            customDialogFragment.show(supportFragmentManager, "CustomDialogFragment")
        }
    }

    private fun showVersionDialog() {
        val context: Context = applicationContext
        val builder = AlertDialog.Builder(context)

        builder.setTitle("Warning For Android 14")
        builder.setMessage("This is a sample dialog message.")
        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss() // Dismiss the dialog
        }
        val dialog = builder.create()
        dialog.show()
    }

    private fun guestMode(guest: Boolean) {
        val sharePref = getSharedPreferences("account", Context.MODE_PRIVATE)
        val editor = sharePref.edit()
        editor.putBoolean("guest", guest)
        editor.apply()
    }

    private fun loadGuest(): Boolean {
        val sharePref = getSharedPreferences("account", Context.MODE_PRIVATE)
        return sharePref.getBoolean("guest", false)
    }

    private fun checkAccessibilityServices() {
        val context: Context = applicationContext
        val manager = context.getSystemService(AccessibilityManager::class.java)
        val enabledServices: List<AccessibilityServiceInfo>? = manager.getEnabledAccessibilityServiceList(AccessibilityServiceInfo.FEEDBACK_ALL_MASK)
        Log.d("logService", "$enabledServices")
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.animate_fade_enter, R.anim.animate_fade_exit)
    }

}