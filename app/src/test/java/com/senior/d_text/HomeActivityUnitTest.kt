package com.senior.d_text

import android.app.Application
import com.senior.d_text.domain.usecase.AnalysisUrlUseCase
import com.senior.d_text.domain.usecase.DeleteAllHistoryUseCase
import com.senior.d_text.domain.usecase.DeleteHistoryUseCase
import com.senior.d_text.domain.usecase.GetHistoryUseCase
import com.senior.d_text.domain.usecase.ListenForMessagesUseCase
import com.senior.d_text.domain.usecase.RefreshUseCase
import com.senior.d_text.domain.usecase.SaveHistoryUseCase
import com.senior.d_text.presentation.authentication.signin.SignInFragment
import com.senior.d_text.presentation.authentication.signin.SignInViewModel
import com.senior.d_text.presentation.home.HomeActivity
import com.senior.d_text.presentation.home.HomeViewModel
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock

class HomeActivityUnitTest {

    private lateinit var vm: HomeViewModel

    private val getHistoryUseCase: GetHistoryUseCase = mockk()
    private val saveHistoryUseCase: SaveHistoryUseCase = mockk()
    private val deleteAllHistoryUseCase: DeleteAllHistoryUseCase = mockk()
    private val application: Application = mockk()
    private val deleteHistoryUseCase: DeleteHistoryUseCase = mockk()
    private val analysisUrlUseCase: AnalysisUrlUseCase = mockk()
    private val refreshUseCase: RefreshUseCase = mockk()

    @Before
    fun setup() {
        vm = HomeViewModel(
            application,
            getHistoryUseCase,
            saveHistoryUseCase,
            deleteAllHistoryUseCase,
            deleteHistoryUseCase,
            analysisUrlUseCase,
            refreshUseCase)
    }

    @Test
    fun addition_isCorrect() {
        Assert.assertEquals(4, 2 + 2)
    }

    @Test
    fun `test validationUrl`() {
        val url1 = "www.example.com"
        val url2 = "http://example.com"
        val url3 = "https://example.com"

        assertEquals("http://www.example.com", vm.validationUrl(url1))
        assertEquals("http://example.com", vm.validationUrl(url2))
        assertEquals("https://example.com", vm.validationUrl(url3))
    }

    @Test
    fun `test extractUrl`() {
        val message = "This is a message with a URL http://www.example.com"
        assertEquals("http://www.example.com", vm.extractUrl(message))
    }
}