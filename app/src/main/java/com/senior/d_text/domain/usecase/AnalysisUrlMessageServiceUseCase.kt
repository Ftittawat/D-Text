package com.senior.d_text.domain.usecase

import com.senior.d_text.data.model.Result
import com.senior.d_text.data.model.analysis.AnalysisUrl
import com.senior.d_text.domain.repository.AnalysisRepository

class AnalysisUrlMessageServiceUseCase(private val analysisRepository: AnalysisRepository) {
    suspend fun execute(url: String, apikey: String): Result<AnalysisUrl?> = analysisRepository.analysisUrl(url, apikey)
}