package com.senior.d_text.domain.usecase

import com.senior.d_text.data.model.authentication.Account
import com.senior.d_text.data.model.Result
import com.senior.d_text.domain.repository.AuthenticationRepository

class RefreshUseCase(private val authenticationRepository: AuthenticationRepository) {
    suspend fun execute(refreshToken: String): Result<Account?> = authenticationRepository.refresh(refreshToken)
}