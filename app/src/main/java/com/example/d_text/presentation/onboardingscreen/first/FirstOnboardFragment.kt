package com.example.d_text.presentation.onboardingscreen.first

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.d_text.R
import com.example.d_text.databinding.FragmentFirstOnboardBinding

class FirstOnboardFragment : Fragment() {

    private lateinit var binding: FragmentFirstOnboardBinding
    private lateinit var vm: FirstOnboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstOnboardBinding.inflate(layoutInflater)

        val viewPager = activity?.findViewById<ViewPager2>(R.id.ViewPager)

        binding.nextButton.setOnClickListener {
            viewPager?.currentItem = 1
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        vm = ViewModelProvider(this)[FirstOnboardViewModel::class.java]
    }

}