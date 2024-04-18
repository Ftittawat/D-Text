package com.senior.d_text.data.repository.authentication.datasource

import retrofit2.Response

interface AuthenticationRemoteDataSource {
    suspend fun signInAccount(email: String, password: String)
    suspend fun signUpAccount(email: String, phone: String, password: String)
    suspend fun signOutAccount()
}