package com.senior.d_text.data.api

import com.senior.d_text.data.model.history.History
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface DTextService {

    @GET("")
    suspend fun getService(): Response<History>

    @POST("")
    suspend fun postService(
        @Query("email") email: String,
        @Query("password") password: String
    ): Response<History>

    @FormUrlEncoded
    @POST("")
    suspend fun postLogin(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<History>
}