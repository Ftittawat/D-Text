package com.senior.d_text.data.repository.authentication.datasourceImpl

import com.senior.d_text.data.api.DTextService
import com.senior.d_text.data.model.authentication.Account
import com.senior.d_text.data.model.authentication.RefreshRequest
import com.senior.d_text.data.model.authentication.ResponseMessage
import com.senior.d_text.data.model.authentication.SignInRequest
import com.senior.d_text.data.model.authentication.SignOutRequest
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

    override suspend fun signOut(request: SignOutRequest): Response<ResponseMessage> {
        return dTextService.signOut(request)
    }

    override suspend fun refresh(request: RefreshRequest): Response<Account> {
        return dTextService.refresh(request)
    }

}