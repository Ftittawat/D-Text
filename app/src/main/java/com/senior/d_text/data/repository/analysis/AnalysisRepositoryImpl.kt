package com.senior.d_text.data.repository.analysis

import com.senior.d_text.data.repository.analysis.datasource.AnalysisRemoteDatasource
import com.senior.d_text.domain.repository.AnalysisRepository

class AnalysisRepositoryImpl(
    private val analysisRemoteDatasource: AnalysisRemoteDatasource
): AnalysisRepository {
    override suspend fun analysisUrl(url: String, type: Int, date_time: String) {
        TODO("Not yet implemented")
    }
}