package com.senior.d_text.domain.usecase

import com.senior.d_text.data.model.history.History
import com.senior.d_text.domain.repository.HistoryRepository
import kotlinx.coroutines.flow.Flow

class GetHistoryUseCase(private val historyRepository: HistoryRepository) {
    fun execute():Flow<List<History>>? = historyRepository.getHistory()
}