package com.senior.d_text.domain.repository

import com.senior.d_text.data.model.analysis.AnalysisUrl

interface AnalysisRepository {
    suspend fun analysisUrl(url: String): AnalysisUrl?
}