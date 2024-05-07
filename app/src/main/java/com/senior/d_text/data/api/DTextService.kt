package com.senior.d_text.data.api

import com.senior.d_text.data.model.analysis.AnalysisUrl
import com.senior.d_text.data.model.analysis.ScanRequest
import com.senior.d_text.data.model.authentication.Account
import com.senior.d_text.data.model.authentication.OAuth2Login
import com.senior.d_text.data.model.authentication.RefreshRequest
import com.senior.d_text.data.model.authentication.ResponseMessage
import com.senior.d_text.data.model.authentication.SignInRequest
import com.senior.d_text.data.model.authentication.SignOutRequest
import com.senior.d_text.data.model.authentication.SignUpRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface DTextService {

//    @GET("check-url")
//    suspend fun analysisUrl(
//        @Query("url") url: String
//    ): Response<AnalysisUrl>

    @POST("v1/dtext-scan")
    suspend fun analysisUrl(
        @Body request: ScanRequest,
        @Header("Authorization") apiKey: String
    ): Response<AnalysisUrl>

    @GET("v1/oauth2/google/user/login")
    suspend fun oAuthLogin(): Response<OAuth2Login>

    @POST("v1/users/signup")
    suspend fun signUp(
        @Body request: SignUpRequest
    ): Response<Account>

    @POST("v1/users/login")
    suspend fun signIn(
        @Body request: SignInRequest
    ): Response<Account>

    @POST("v1/users/logout")
    suspend fun signOut(
        @Body request: SignOutRequest
    ): Response<ResponseMessage>

    @POST("v1/users/refresh")
    suspend fun refresh(
        @Body request: RefreshRequest
    ): Response<Account>

    @FormUrlEncoded
    @POST("v1/dtext-scan")
    suspend fun postAnalysisUrl(
        @Field("url") url: String,
    ): AnalysisUrl

    @DELETE
    suspend fun logout(

    )
}