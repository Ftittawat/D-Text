package com.senior.d_text.domain.repository

import com.senior.d_text.data.model.authentication.Account
import com.senior.d_text.data.model.authentication.ResponseMessage
import com.senior.d_text.data.model.Result

interface AuthenticationRepository {
    suspend fun signIn(username: String, password: String): Result<Account?>
    suspend fun signUp(username: String, email: String, phone: String, password: String): Result<Account?>
    suspend fun signOut(authId: String): ResponseMessage?
    suspend fun refresh(refreshToken: String): Result<Account?>
}