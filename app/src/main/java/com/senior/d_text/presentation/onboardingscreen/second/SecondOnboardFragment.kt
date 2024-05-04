package com.senior.d_text.presentation.onboardingscreen.second

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.senior.d_text.R
import com.senior.d_text.databinding.FragmentSecondOnboardBinding

class SecondOnboardFragment : Fragment() {

    private lateinit var binding: FragmentSecondOnboardBinding
    private lateinit var vm: SecondOnboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondOnboardBinding.inflate(layoutInflater)

        val viewPager = activity?.findViewById<ViewPager2>(R.id.ViewPager)

        binding.nextButton.setOnClickListener {
            viewPager?.currentItem = 2
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        vm = ViewModelProvider(this)[SecondOnboardViewModel::class.java]
    }

}