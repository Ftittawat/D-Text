package com.senior.d_text.data.repository.history.datasource

import com.senior.d_text.data.model.history.History
import retrofit2.Response

interface AnalysisRemoteDatasource {
    suspend fun analysisUrl(): Response<History>
}