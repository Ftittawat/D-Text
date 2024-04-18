package com.senior.d_text.data.repository.analysis.datasourceImpl

import com.senior.d_text.data.api.DTextService
import com.senior.d_text.data.repository.analysis.datasource.AnalysisRemoteDatasource

class AnalysisRemoteDatasourceImpl(
    private val dTextService: DTextService
): AnalysisRemoteDatasource {
    override suspend fun analysisUrl() = dTextService.postService("1","")
}