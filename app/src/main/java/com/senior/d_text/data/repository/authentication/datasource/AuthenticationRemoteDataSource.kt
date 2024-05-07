package com.senior.d_text.data.repository.authentication.datasource

import com.senior.d_text.data.model.authentication.Account
import com.senior.d_text.data.model.authentication.RefreshRequest
import com.senior.d_text.data.model.authentication.ResponseMessage
import com.senior.d_text.data.model.authentication.SignInRequest
import com.senior.d_text.data.model.authentication.SignOutRequest
import com.senior.d_text.data.model.authentication.SignUpRequest
import retrofit2.Response

interface AuthenticationRemoteDataSource {
    suspend fun signIn(request: SignInRequest): Response<Account>
    suspend fun signUp(request: SignUpRequest): Response<Account>
    suspend fun signOut(request: SignOutRequest): Response<ResponseMessage>
    suspend fun refresh(request: RefreshRequest): Response<Account>
}