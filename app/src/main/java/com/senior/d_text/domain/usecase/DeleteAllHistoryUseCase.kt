package com.senior.d_text.domain.usecase

import com.senior.d_text.domain.repository.HistoryRepository

class DeleteAllHistoryUseCase(private val historyRepository: HistoryRepository) {
    suspend fun execute() = historyRepository.deleteAllHistory()
}