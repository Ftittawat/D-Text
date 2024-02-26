package com.example.d_text.presentation.signIn

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.example.d_text.databinding.ActivitySigninBinding
import com.example.d_text.presentation.acceptpolicy.AcceptPolicyActivity
import com.example.d_text.presentation.core.SignInViewModelFactory
import com.example.d_text.presentation.home.HomeActivity
import javax.inject.Inject

class SignInActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: SignInViewModelFactory
    private lateinit var signInViewModel: SignInViewModel
    private lateinit var binding: ActivitySigninBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        signInViewModel = ViewModelProvider(this, factory).get(SignInViewModel::class.java)

        binding.signInButton.setOnClickListener {
            intent = Intent(this, AcceptPolicyActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        setupButton()
    }

    private fun setupButton() {
        binding.bottomContainer.setOnClickListener {
            it.hideKeyboard()
        }

        binding.closeButton.setOnClickListener {
            onBackPressed()
        }
    }

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}