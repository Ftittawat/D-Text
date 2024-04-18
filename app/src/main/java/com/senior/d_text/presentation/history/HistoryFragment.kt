package com.senior.d_text.presentation.history

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.senior.d_text.R
import com.senior.d_text.databinding.FragmentHistoryBinding

class HistoryFragment : BottomSheetDialogFragment() {

    private lateinit var vm: HistoryViewModel
    private lateinit var  binding: FragmentHistoryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentHistoryBinding.inflate(layoutInflater)
        vm = ViewModelProvider(this)[HistoryViewModel::class.java]
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        setupView()
    }

    override fun onResume() {
        super.onResume()
        setupButton()
    }

    private fun setupView() {
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        val bottomSheet: View? = dialog?.findViewById(com.google.android.material.R.id.design_bottom_sheet)
        bottomSheet?.layoutParams?.height = ViewGroup.LayoutParams.MATCH_PARENT
        setStyle(STYLE_NORMAL, R.style.FullScreenBottomSheetDialog)
        bottomSheet?.let {
            val behavior = BottomSheetBehavior.from(it)
            behavior.isDraggable = false
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }

        vm.url.value = arguments?.getString("URL","")
        vm.riskLevel.value = arguments?.getString("RISK_LEVEL","")
        vm.urlType.value = arguments?.getString("TYPE","")
        vm.dateTime.value = arguments?.getString("DATE_TIME","")

        binding.url.text = vm.url.value

        when (vm.riskLevel.value) {
            "safe" -> {
                binding.riskLevelTitle.text = getText(R.string.safe)
                val state = intArrayOf(R.attr.state_safe)
                binding.resultIcon.setImageState(state, true)
                binding.riskLevelLine.setImageState(state, true)
                binding.warningText.isGone = true
                binding.browserContainer.isVisible = true
                binding.typeTitle.isGone = true
                binding.typeResult.isGone = true
                binding.organizationTitle.isVisible = true
                binding.organizationResult.isVisible = true
            }
            "suspicious" -> {
                binding.riskLevelTitle.text = getText(R.string.suspicious)
                val state = intArrayOf(R.attr.state_suspicious)
                binding.resultIcon.setImageState(state, true)
                binding.riskLevelLine.setImageState(state, true)
                binding.warningText.isVisible = true
                binding.browserContainer.isGone = true
                binding.typeTitle.isGone = true
                binding.typeResult.isGone = true
                binding.organizationTitle.isVisible = true
                binding.organizationResult.isVisible = true
            }
            "unsafe" -> {
                binding.riskLevelTitle.text = getText(R.string.unsafe)
                val state = intArrayOf(R.attr.state_unsafe)
                binding.resultIcon.setImageState(state, true)
                binding.riskLevelLine.setImageState(state, true)
                binding.warningText.isVisible = true
                binding.browserContainer.isGone = true
                binding.typeTitle.isVisible = true
                binding.typeResult.isVisible = true
                binding.organizationTitle.isGone = true
                binding.organizationResult.isGone = true
            }
            else -> {
                binding.riskLevelTitle.text = getText(R.string.no_information)
                val state = intArrayOf(R.attr.state_no_information)
                binding.resultIcon.setImageState(state, true)
                binding.riskLevelLine.setImageState(state, true)
                binding.warningText.isGone = true
                binding.browserContainer.isGone = true
                binding.typeContainer.isGone = true
            }
        }

    }

    private fun setupButton() {
        binding.closeButton.setOnClickListener {
            dismiss()
        }
        binding.browserButton.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(vm.url.value.toString()))
            startActivity(browserIntent)
        }
    }
}