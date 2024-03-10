package com.example.d_text.presentation.onboardingscreen.third

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.d_text.R
import com.example.d_text.databinding.FragmentThirdOnboardBinding
import com.example.d_text.presentation.onboardingscreen.OnboardActivity
import com.example.d_text.presentation.welcomescreen.WelcomeScreenActivity

class ThirdOnboardFragment : Fragment() {

    private lateinit var binding: FragmentThirdOnboardBinding
    private lateinit var vm: ThirdOnboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentThirdOnboardBinding.inflate(layoutInflater)

        binding.nextButton.setOnClickListener {
            val intent = Intent (activity, WelcomeScreenActivity::class.java)
            activity?.startActivity(intent)
            onBoardFinished()
            // Log.d("log", "${onBoardFinished()}")
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        vm = ViewModelProvider(this)[ThirdOnboardViewModel::class.java]
    }

    private fun onBoardFinished() {
        val sharePref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharePref.edit()
        editor.putBoolean("finish", true)
        editor.apply()
    }
}