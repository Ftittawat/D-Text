package com.senior.d_text.domain.usecase

import com.senior.d_text.domain.repository.AuthenticationRepository

class SignOutUseCase(private val authenticationRepository: AuthenticationRepository) {
    suspend fun execute(id: String) = authenticationRepository.signOut(id)
}