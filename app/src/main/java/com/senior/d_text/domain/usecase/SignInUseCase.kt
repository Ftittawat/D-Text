package com.senior.d_text.domain.usecase

import com.senior.d_text.data.model.authentication.Account
import com.senior.d_text.data.model.authentication.Result
import com.senior.d_text.domain.repository.AuthenticationRepository

class SignInUseCase(private val authenticationRepository: AuthenticationRepository) {
    suspend fun execute(username: String, password: String): Result<Account?> = authenticationRepository.signIn(username, password)
}