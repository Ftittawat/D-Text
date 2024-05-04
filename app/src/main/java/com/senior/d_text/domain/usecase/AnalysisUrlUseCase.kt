package com.senior.d_text.domain.usecase

import com.senior.d_text.data.model.analysis.AnalysisUrl
import com.senior.d_text.domain.repository.AnalysisRepository

class AnalysisUrlUseCase(private val analysisRepository: AnalysisRepository) {
    suspend fun execute(url: String): AnalysisUrl? = analysisRepository.analysisUrl(url)
}