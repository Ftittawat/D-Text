package com.senior.d_text.presentation.history

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.senior.d_text.R
import com.senior.d_text.data.model.history.History
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

        val linkData = arguments?.getSerializable("LINK_DATA") as History?

//        vm.url.value = arguments?.getString("URL","")
//        vm.riskLevel.value = arguments?.getString("RISK_LEVEL","")
//        vm.urlType.value = arguments?.getString("TYPE","")
//        vm.orgName.value = arguments?.getString("ORG_NAME","")
//        vm.dateTime.value = arguments?.getString("DATE_TIME","")

        vm.url.value = linkData?.url
        vm.riskLevel.value = linkData?.risk_level
        vm.dTextLevel.value = linkData?.d_text_level
        vm.googleLevel.value = linkData?.google_level
        vm.type.value = linkData?.type
        vm.dateTime.value = linkData?.date_time
        vm.domainAgeDay.value = linkData?.domainAgeDay
        vm.hasForm.value = linkData?.hasForm
        vm.hasIframe.value = linkData?.hasIframe
        vm.hasShortened.value = linkData?.hasShortened
        vm.hasSsl.value = linkData?.hasSsl
        vm.urlScore.value = linkData?.urlScore

        binding.url.text = vm.url.value
        binding.checklist1Result.text = vm.validationLevel(vm.hasShortened.value!!)
        binding.checklist2Result.text = vm.validationLevel(vm.hasSsl.value!!)
        binding.checklist3Result.text = vm.validationLevel(vm.hasIframe.value!!)
        binding.checklist4Result.text = vm.validationLevel(vm.hasForm.value!!)
        binding.checklist5Result.text = vm.domainAgeDay.value.toString()

        Log.d("analysisResult", "setupView: ${linkData.toString()}")

        when (vm.riskLevel.value) {
            "safe" -> {
                binding.riskLevelTitle.text = getText(R.string.safe)
                val state = intArrayOf(R.attr.state_safe)
                binding.organizationResult.text = vm.type.value
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
                binding.organizationResult.text = vm.type.value
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
                binding.typeResult.text = vm.type.value
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

        when (vm.dTextLevel.value) {
            UNSAFE -> {
                binding.checklistResult.text = getText(R.string.result_unsafe)
                val color = ContextCompat.getColor(requireContext(), R.color.text_red)
                binding.checklistResult.setTextColor(color)
            }
            SUSPICIOUS -> {
                binding.checklistResult.text = getText(R.string.result_suspicious)
                val color = ContextCompat.getColor(requireContext(), R.color.text_yellow)
                binding.checklistResult.setTextColor(color)
            }
            SAFE -> {
                binding.checklistResult.text = getText(R.string.result_safe)
                val color = ContextCompat.getColor(requireContext(), R.color.text_green)
                binding.checklistResult.setTextColor(color)
            } else -> {
                binding.checklistResult.text = getText(R.string.not_found)
            }
        }

        when (vm.googleLevel.value) {
            UNSAFE -> {
                binding.googleResult.text = getText(R.string.result_unsafe)
                val color = ContextCompat.getColor(requireContext(), R.color.text_red)
                binding.googleResult.setTextColor(color)
            }
            SAFE -> {
                binding.googleResult.text = getText(R.string.result_safe)
                val color = ContextCompat.getColor(requireContext(), R.color.text_green)
                binding.googleResult.setTextColor(color)
            } else -> {
            binding.googleResult.text = getText(R.string.not_found)
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

    companion object {
        const val UNSAFE = "unsafe"
        const val SUSPICIOUS = "suspicious"
        const val SAFE = "safe"
        const val NO_INFORMATION = "no_information"
    }
}