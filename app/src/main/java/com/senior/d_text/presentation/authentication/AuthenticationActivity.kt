package com.senior.d_text.presentation.authentication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.senior.d_text.R
import com.senior.d_text.databinding.ActivityAuthenticationBinding
import com.senior.d_text.presentation.authentication.signin.SignInFragment
import com.senior.d_text.presentation.authentication.signup.SignUpFragment
import com.senior.d_text.presentation.core.AuthenticationViewModelFactory
import com.senior.d_text.presentation.di.Injector
import javax.inject.Inject

class AuthenticationActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: AuthenticationViewModelFactory
    private lateinit var vm: AuthenticationViewModel
    private lateinit var binding: ActivityAuthenticationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthenticationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        (application as Injector).createAuthenticationSubComponent()
            .inject(this)
        vm = ViewModelProvider(this)[AuthenticationViewModel::class.java]
    }

    override fun onResume() {
        super.onResume()
        setupView()
    }

    private fun setupView() {
        val page = intent.extras?.getBoolean("sign_in")
        if (page == true) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, SignInFragment())
                .commit()
        } else (
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, SignUpFragment())
                .commit()
        )
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.animate_fade_enter, R.anim.animate_fade_exit)
    }
}