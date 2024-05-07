package com.senior.d_text.presentation.di.messageService

import com.senior.d_text.domain.repository.AnalysisRepository
import com.senior.d_text.domain.repository.MessageRepository
import com.senior.d_text.domain.repository.NotificationRepository
import com.senior.d_text.domain.usecase.AnalysisUrlMessageServiceUseCase
import com.senior.d_text.domain.usecase.SaveMessageUseCase
import com.senior.d_text.domain.usecase.SaveNotificationForMessageUseCase
import dagger.Module
import dagger.Provides

@Module
class MessageServiceModule {
    @Provides
    fun provideSaveMessage(
        messageRepository: MessageRepository
    ): SaveMessageUseCase {
        return SaveMessageUseCase(messageRepository)
    }

    @Provides
    fun provideSaveNotificationForMessageUseCase(
        notificationRepository: NotificationRepository
    ): SaveNotificationForMessageUseCase {
        return SaveNotificationForMessageUseCase(notificationRepository)
    }

//    @Provides
//    fun provideAnalysisUrlUseCase(
//        analysisRepository: AnalysisRepository
//    ): AnalysisUrlUseCase {
//        return AnalysisUrlUseCase(analysisRepository)
//    }

    @Provides
    fun provideAnalysisUrlMessageServiceUseCase(
        analysisRepository: AnalysisRepository
    ): AnalysisUrlMessageServiceUseCase {
        return AnalysisUrlMessageServiceUseCase(analysisRepository)
    }

//    @Provides
//    fun provideMessageServiceViewModelFactory(
//        analysisUrlUseCase: AnalysisUrlUseCase
//    ): MessageServiceViewModelFactory {
//        return MessageServiceViewModelFactory(
//            analysisUrlUseCase
//        )
//    }
}
