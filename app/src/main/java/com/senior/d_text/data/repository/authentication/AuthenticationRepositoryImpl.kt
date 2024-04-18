package com.senior.d_text.data.repository.authentication

import com.senior.d_text.data.repository.authentication.datasource.AuthenticationRemoteDataSource
import com.senior.d_text.domain.repository.AuthenticationRepository

class AuthenticationRepositoryImpl(
    private val authenticationRemoteDataSource: AuthenticationRemoteDataSource
): AuthenticationRepository {
    override suspend fun signIn(email: String, password: String) {
        TODO("Not yet implemented")
    }

    override suspend fun signUp(email: String, phone: String, password: String) {
        TODO("Not yet implemented")
    }

    override suspend fun signOut() {
        TODO("Not yet implemented")
    }
}