package com.senior.d_text.domain.usecase

import com.senior.d_text.data.model.authentication.Account
import com.senior.d_text.data.model.authentication.Result
import com.senior.d_text.domain.repository.AuthenticationRepository

class SignUpUseCase(private val authenticationRepository: AuthenticationRepository) {
    suspend fun execute(
        username: String,
        email: String,
        password: String,
        phone: String
    ): Result<Account?> = authenticationRepository.signUp(username, email, phone, password)
}