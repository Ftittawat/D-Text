package com.senior.d_text.data.repository.authentication.datasourceImpl

import com.senior.d_text.data.api.DTextService
import com.senior.d_text.data.repository.authentication.datasource.AuthenticationRemoteDataSource

class AuthenticationRemoteDataSourceImpl(
    private val dTextService: DTextService
): AuthenticationRemoteDataSource {
    override suspend fun signInAccount(email: String, password: String) {
        TODO("Not yet implemented")
    }

    override suspend fun signUpAccount(email: String, phone: String, password: String) {
        TODO("Not yet implemented")
    }

    override suspend fun signOutAccount() {
        TODO("Not yet implemented")
    }
}