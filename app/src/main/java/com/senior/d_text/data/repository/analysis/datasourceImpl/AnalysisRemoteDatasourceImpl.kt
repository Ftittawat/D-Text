package com.senior.d_text.data.repository.analysis.datasourceImpl

import com.senior.d_text.data.api.DTextService
import com.senior.d_text.data.model.analysis.AnalysisUrl
import com.senior.d_text.data.model.analysis.ScanRequest
import com.senior.d_text.data.repository.analysis.datasource.AnalysisRemoteDatasource
import retrofit2.Response

class AnalysisRemoteDatasourceImpl(
    private val dTextService: DTextService
): AnalysisRemoteDatasource {
    override suspend fun analysisUrl(url: String): Response<AnalysisUrl> {
        val request = ScanRequest(url)
        return dTextService.analysisUrl(request)
    }
}