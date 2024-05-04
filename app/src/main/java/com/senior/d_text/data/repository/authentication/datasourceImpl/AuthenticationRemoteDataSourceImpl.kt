package com.senior.d_text.data.repository.authentication.datasourceImpl

import com.senior.d_text.data.api.DTextService
import com.senior.d_text.data.model.authentication.Account
import com.senior.d_text.data.model.authentication.SignInRequest
import com.senior.d_text.data.model.authentication.SignUpRequest
import com.senior.d_text.data.repository.authentication.datasource.AuthenticationRemoteDataSource
import retrofit2.Response

class AuthenticationRemoteDataSourceImpl(
    private val dTextService: DTextService
): AuthenticationRemoteDataSource {
    override suspend fun signIn(request: SignInRequest): Response<Account> {
        return dTextService.signIn(request)
    }

    override suspend fun signUp(request: SignUpRequest): Response<Account> {
        return dTextService.signUp(request)
    }

}