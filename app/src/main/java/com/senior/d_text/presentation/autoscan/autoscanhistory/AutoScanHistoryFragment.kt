package com.senior.d_text.presentation.autoscan.autoscanhistory

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
import com.senior.d_text.data.model.notification.Notification
import com.senior.d_text.databinding.FragmentAutoScanHistoryBinding
import com.senior.d_text.presentation.history.HistoryFragment

class AutoScanHistoryFragment: BottomSheetDialogFragment() {

    private lateinit var binding: FragmentAutoScanHistoryBinding
    private lateinit var vm: AutoScanHistoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAutoScanHistoryBinding.inflate(layoutInflater)
        vm = ViewModelProvider(this)[AutoScanHistoryViewModel::class.java]
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
            behavior.isDraggable = true
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }

        val notificationData = arguments?.getSerializable("NOTIFICATION_DATA") as Notification?
        Log.d("logMessages", "setupView: $notificationData")

        vm.url.value = notificationData?.url
        vm.riskLevel.value = notificationData?.risk_level
        vm.dTextLevel.value = notificationData?.d_text_level
        vm.googleLevel.value = notificationData?.google_level
        vm.application.value = notificationData?.application
        vm.source.value = notificationData?.source
        vm.type.value = notificationData?.type
        vm.dateTime.value = notificationData?.date_time
        vm.domainAgeDay.value = notificationData?.domainAgeDay
        vm.hasForm.value = notificationData?.hasForm
        vm.hasIframe.value = notificationData?.hasIframe
        vm.hasShortened.value = notificationData?.hasShortened
        vm.hasSsl.value = notificationData?.hasSsl
        vm.urlScore.value = notificationData?.urlScore

        binding.url.text = vm.url.value
        binding.sourceResult.text = vm.source.value
        binding.checklist1Result.text = vm.validationLevel(vm.hasShortened.value!!)
        binding.checklist2Result.text = vm.validationLevel(vm.hasSsl.value!!)
        binding.checklist3Result.text = vm.validationLevel(vm.hasIframe.value!!)
        binding.checklist4Result.text = vm.validationLevel(vm.hasForm.value!!)
        binding.checklist5Result.text = vm.domainAgeDay.value.toString()

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
            HistoryFragment.UNSAFE -> {
                binding.checklistResult.text = getText(R.string.result_unsafe)
                val color = ContextCompat.getColor(requireContext(), R.color.text_red)
                binding.checklistResult.setTextColor(color)
            }
            HistoryFragment.SUSPICIOUS -> {
                binding.checklistResult.text = getText(R.string.result_suspicious)
                val color = ContextCompat.getColor(requireContext(), R.color.text_yellow)
                binding.checklistResult.setTextColor(color)
            }
            HistoryFragment.SAFE -> {
                binding.checklistResult.text = getText(R.string.result_safe)
                val color = ContextCompat.getColor(requireContext(), R.color.text_green)
                binding.checklistResult.setTextColor(color)
            } else -> {
            binding.checklistResult.text = getText(R.string.not_found)
        }
        }

        when (vm.googleLevel.value) {
            HistoryFragment.UNSAFE -> {
                binding.googleResult.text = getText(R.string.result_unsafe)
                val color = ContextCompat.getColor(requireContext(), R.color.text_red)
                binding.googleResult.setTextColor(color)
            }
            HistoryFragment.SAFE -> {
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
            val url = vm.validationUrl(vm.url.value.toString())
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
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