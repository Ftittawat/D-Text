package com.senior.d_text.domain.usecase

import com.senior.d_text.data.model.authentication.ResponseMessage
import com.senior.d_text.domain.repository.AuthenticationRepository

class SignOutUseCase(private val authenticationRepository: AuthenticationRepository) {
    suspend fun execute(authId: String): ResponseMessage? = authenticationRepository.signOut(authId)
}