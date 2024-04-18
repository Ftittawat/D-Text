package com.senior.d_text.domain.repository

interface AnalysisRepository {
    suspend fun analysisUrl(url: String, type: Int, date_time: String)
}