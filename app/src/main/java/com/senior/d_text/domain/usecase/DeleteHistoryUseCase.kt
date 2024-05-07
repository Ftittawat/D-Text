package com.senior.d_text.domain.usecase

import com.senior.d_text.domain.repository.HistoryRepository

class DeleteHistoryUseCase(private val historyRepository: HistoryRepository) {
    suspend fun execute(id: Int) = historyRepository.deleteHistory(id)
}