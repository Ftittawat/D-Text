package com.senior.d_text.data.repository.authentication

import android.util.Log
import com.google.gson.Gson
import com.senior.d_text.data.model.authentication.Account
import com.senior.d_text.data.model.authentication.Result
import com.senior.d_text.data.model.authentication.SignInRequest
import com.senior.d_text.data.model.authentication.SignUpRequest
import com.senior.d_text.data.repository.authentication.datasource.AuthenticationRemoteDataSource
import com.senior.d_text.domain.repository.AuthenticationRepository
import java.lang.Exception

class AuthenticationRepositoryImpl(
    private val authenticationRemoteDataSource: AuthenticationRemoteDataSource
): AuthenticationRepository {
    override suspend fun signIn(
        username: String,
        password: String
    ): Result<Account?> {
        return try {
            val request = SignInRequest(username, password)
            val response = authenticationRemoteDataSource.signIn(request)
            if (response.isSuccessful) {
                val accountResponse = response.body()
                Result.Success(accountResponse)
            } else {
                val errorBody = response.errorBody()?.string()
                val errorResponse = Gson().fromJson(errorBody, Account::class.java)
                Log.d("Auth", "signIn: $errorResponse")
                Result.Error(errorResponse.error ?: "")
            }
        } catch (e: Exception) {
            Result.Error("Network error")
        }
    }

    override suspend fun signUp(
        username: String,
        email: String,
        phone: String,
        password: String,
    ): Result<Account?> {
        return try {
            val request = SignUpRequest(username, email, password, phone)
            val response = authenticationRemoteDataSource.signUp(request)
            if (response.isSuccessful) {
                val accountResponse = response.body()
                Result.Success(accountResponse)
            } else {
                val errorBody = response.errorBody()?.string()
                val errorResponse = Gson().fromJson(errorBody, Account::class.java)
                Log.d("Auth", "signUp: $errorResponse")
                Result.Error(errorResponse.error ?: "")
            }
        } catch (e: Exception) {
            Result.Error("Network error")
        }
    }

    override suspend fun signOut(id: String) {
        TODO("Not yet implemented")
    }

}