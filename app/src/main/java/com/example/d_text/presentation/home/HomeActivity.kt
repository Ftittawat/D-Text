package com.example.d_text.presentation.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.d_text.databinding.ActivityHomeBinding
import com.example.d_text.presentation.onboardingscreen.first.FirstOnboardFragment
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: HomeViewModelFactory
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        homeViewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)
        Handler().postDelayed({
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(binding.fragmentContainer.id, FirstOnboardFragment.newInstance())
            fragmentTransaction.commit()
        }, 2000)
    }
}