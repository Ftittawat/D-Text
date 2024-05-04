package com.senior.d_text

import com.senior.d_text.presentation.authentication.signin.SignInFragment
import com.senior.d_text.presentation.authentication.signin.SignInViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class SignInActivityUnitTest {

    @Mock
    private lateinit var view: SignInFragment
    private lateinit var vm: SignInViewModel

    @Test
    fun addition_isCorrect() {
        Assert.assertEquals(4, 2 + 2)
    }
}