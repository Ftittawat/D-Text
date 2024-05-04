package com.senior.d_text.data.repository.analysis

import android.util.Log
import com.senior.d_text.data.model.analysis.AnalysisUrl
import com.senior.d_text.data.repository.analysis.datasource.AnalysisRemoteDatasource
import com.senior.d_text.domain.repository.AnalysisRepository
import java.lang.Exception

class AnalysisRepositoryImpl(
    private val analysisRemoteDatasource: AnalysisRemoteDatasource
): AnalysisRepository {
    override suspend fun analysisUrl(url: String): AnalysisUrl? {
        var analysisResult: AnalysisUrl? = null
        try {
            val response = analysisRemoteDatasource.analysisUrl(url)
            val body = response.body()
            if (body!=null) {
                analysisResult = body
                Log.d("analysisResult", "analysisUrl: $analysisResult")
            }
        } catch (exception: Exception) {
            Log.i("catch", exception.message.toString())
        }
        return analysisResult
    }
}