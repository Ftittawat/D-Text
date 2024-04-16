package com.senior.d_text.data.repository.history.datasourceImpl

import com.senior.d_text.data.api.DTextService
import com.senior.d_text.data.repository.history.datasource.AnalysisRemoteDatasource

class AnalysisRemoteDatasourceImpl(
    private val dTextService: DTextService
): AnalysisRemoteDatasource {
    override suspend fun analysisUrl() = dTextService.postService("1","")
}