package com.senior.d_text.domain.repository

interface AuthenticationRepository {
    suspend fun signIn(email: String, password: String)
    suspend fun signUp(email: String, phone: String, password: String)
    suspend fun signOut()
}