package com.senior.d_text.data.repository.analysis.datasource

import com.senior.d_text.data.model.analysis.AnalysisUrl
import com.senior.d_text.data.model.history.History
import retrofit2.Response

interface AnalysisRemoteDatasource {
    suspend fun analysisUrl(url: String, apikey: String): Response<AnalysisUrl>
}