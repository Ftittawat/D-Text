package com.senior.d_text.data.repository.authentication.datasource

import com.senior.d_text.data.model.authentication.Account
import com.senior.d_text.data.model.authentication.SignInRequest
import com.senior.d_text.data.model.authentication.SignUpRequest
import retrofit2.Response

interface AuthenticationRemoteDataSource {
    suspend fun signIn(request: SignInRequest): Response<Account>
    suspend fun signUp(request: SignUpRequest): Response<Account>
}