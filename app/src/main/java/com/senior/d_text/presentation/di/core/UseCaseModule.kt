package com.senior.d_text.presentation.di.core

import com.senior.d_text.domain.repository.AnalysisRepository
import com.senior.d_text.domain.repository.AuthenticationRepository
import com.senior.d_text.domain.repository.HistoryRepository
import com.senior.d_text.domain.repository.MessageRepository
import com.senior.d_text.domain.repository.NotificationHistoryRepository
import com.senior.d_text.domain.repository.SMSRepository
import com.senior.d_text.domain.usecase.AnalysisUrlMessageServiceUseCase
import com.senior.d_text.domain.usecase.AnalysisUrlUseCase
import com.senior.d_text.domain.usecase.DeleteAllHistoryUseCase
import com.senior.d_text.domain.usecase.DeleteAllMessageUseCase
import com.senior.d_text.domain.usecase.DeleteAllNotificationHistoryUseCase
import com.senior.d_text.domain.usecase.ListenForMessagesUseCase
import com.senior.d_text.domain.usecase.GetHistoryUseCase
import com.senior.d_text.domain.usecase.GetMessageUseCase
import com.senior.d_text.domain.usecase.GetNotificationHistoryUseCase
import com.senior.d_text.domain.usecase.SaveHistoryUseCase
import com.senior.d_text.domain.usecase.SignInUseCase
import com.senior.d_text.domain.usecase.SignOutUseCase
import com.senior.d_text.domain.usecase.SignUpUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun provideListenForMessagesUseCase(smsRepository: SMSRepository): ListenForMessagesUseCase {
        return ListenForMessagesUseCase((smsRepository))
    }

    @Provides
    fun provideGetHistoryUseCase(historyRepository: HistoryRepository): GetHistoryUseCase {
        return GetHistoryUseCase(historyRepository)
    }

    @Provides
    fun provideSaveHistoryUseCase(historyRepository: HistoryRepository): SaveHistoryUseCase {
        return SaveHistoryUseCase(historyRepository)
    }

    @Provides
    fun provideDeleteAllHistoryUseCase(historyRepository: HistoryRepository): DeleteAllHistoryUseCase {
        return DeleteAllHistoryUseCase(historyRepository)
    }

//    @Provides
//    fun provideSaveMessage(messageRepository: MessageRepository): SaveMessageUseCase {
//        return SaveMessageUseCase(messageRepository)
//    }

    @Provides
    fun provideGetMessageUseCase(messageRepository: MessageRepository): GetMessageUseCase {
        return GetMessageUseCase(messageRepository)
    }

    @Provides
    fun provideDeleteAllMessageUseCase(messageRepository: MessageRepository): DeleteAllMessageUseCase {
        return DeleteAllMessageUseCase(messageRepository)
    }

    @Provides
    fun provideGetNotificationHistoryUseCase(notificationHistoryRepository: NotificationHistoryRepository): GetNotificationHistoryUseCase {
        return GetNotificationHistoryUseCase(notificationHistoryRepository)
    }

    @Provides
    fun provideDeleteAllNotificationHistoryUseCase(notificationHistoryRepository: NotificationHistoryRepository): DeleteAllNotificationHistoryUseCase {
        return DeleteAllNotificationHistoryUseCase(notificationHistoryRepository)
    }

    @Provides
    fun provideAnalysisUrlUseCase(analysisRepository: AnalysisRepository): AnalysisUrlUseCase {
        return AnalysisUrlUseCase(analysisRepository)
    }

//    @Provides
//    fun provideAnalysisUrlMessageServiceUseCase(analysisRepository: AnalysisRepository): AnalysisUrlMessageServiceUseCase {
//        return AnalysisUrlMessageServiceUseCase(analysisRepository)
//    }

    @Provides
    fun provideSignInUseCase(authenticationRepository: AuthenticationRepository): SignInUseCase {
        return SignInUseCase(authenticationRepository)
    }

    @Provides
    fun provideSignUpUseCase(authenticationRepository: AuthenticationRepository): SignUpUseCase {
        return SignUpUseCase(authenticationRepository)
    }

    @Provides
    fun provideSignOutUseCase(authenticationRepository: AuthenticationRepository): SignOutUseCase {
        return SignOutUseCase(authenticationRepository)
    }
}