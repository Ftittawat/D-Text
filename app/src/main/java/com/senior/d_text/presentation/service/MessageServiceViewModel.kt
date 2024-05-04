package com.senior.d_text.presentation.service

import androidx.lifecycle.ViewModel
import com.senior.d_text.domain.usecase.AnalysisUrlMessageServiceUseCase
import com.senior.d_text.domain.usecase.AnalysisUrlUseCase
import javax.inject.Inject

class MessageServiceViewModel @Inject constructor (
    private val analysisUrlMessageServiceUseCase: AnalysisUrlMessageServiceUseCase
): ViewModel() {

}