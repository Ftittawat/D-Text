package com.senior.d_text.domain.usecase

import com.senior.d_text.data.model.history.History
import com.senior.d_text.domain.repository.HistoryRepository

class SaveHistoryUseCase(private val historyRepository: HistoryRepository) {
    suspend fun execute(history: History) = historyRepository.saveHistory(history)
}