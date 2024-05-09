package com.senior.d_text.data.repository.analysis

import android.util.Log
import com.google.gson.Gson
import com.senior.d_text.data.model.Result
import com.senior.d_text.data.model.analysis.AnalysisUrl
import com.senior.d_text.data.repository.analysis.datasource.AnalysisRemoteDatasource
import com.senior.d_text.domain.repository.AnalysisRepository
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception

class AnalysisRepositoryImpl(
    private val analysisRemoteDatasource: AnalysisRemoteDatasource
): AnalysisRepository {
    //    override suspend fun analysisUrl(url: String, apikey: String): AnalysisUrl? {
//        var analysisResult: AnalysisUrl? = null
//        try {
//            val response = analysisRemoteDatasource.analysisUrl(url, apikey)
//            val body = response.body()
//            if (body!=null) {
//                analysisResult = body
//                Log.d("analysisResult", "analysisUrl: $analysisResult")
//            }
//        } catch (exception: Exception) {
//            Log.i("catch", exception.message.toString())
//        }
//        return analysisResult
//    }
    override suspend fun analysisUrl(url: String, apikey: String): Result<AnalysisUrl?> {
        return try {
            val response = analysisRemoteDatasource.analysisUrl(url, apikey)
            if (response.isSuccessful) {
                val body = response.body()
                Result.Success(body)
            } else {
                val errorBody = response.errorBody()?.string()
                val errorResponse = Gson().fromJson(errorBody, AnalysisUrl::class.java)
                Result.Error(errorResponse.error ?: "")
            }
        } catch (e: Exception) {
            Log.i("log", "analysisUrl: ${e.message}")
            Result.Error("Unknown error")
        } catch (e: IOException) {
            Log.i("log", "analysisUrl: ${e.message}")
            Result.Error("Network error")
        } catch (e: HttpException) {
            Log.i("log", "analysisUrl: ${e.code()}: ${e.message()}")
            Result.Error("${e.code()}")
        }
    }
}