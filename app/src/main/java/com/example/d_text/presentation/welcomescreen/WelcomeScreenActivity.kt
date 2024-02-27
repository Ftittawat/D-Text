package com.example.d_text.presentation.welcomescreen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.d_text.databinding.ActivityWelcomeBinding
import com.example.d_text.presentation.authentication.AuthenticationActivity
import com.example.d_text.presentation.signIn.SignInActivity
import com.example.d_text.presentation.signUp.SignUpActivity

class WelcomeScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signInButton.setOnClickListener {
            intent = Intent(this, AuthenticationActivity::class.java)
            intent.putExtra("sign_in", true)
            startActivity(intent)
        }

        binding.signUpButton.setOnClickListener {
            intent = Intent(this, AuthenticationActivity::class.java)
            intent.putExtra("sign_in", false)
            startActivity(intent)
        }
    }

}