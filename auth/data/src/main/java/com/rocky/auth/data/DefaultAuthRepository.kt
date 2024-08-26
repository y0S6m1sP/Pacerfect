package com.rocky.auth.data

import com.rocky.auth.data.di.LoginResponse
import com.rocky.auth.domain.AuthRepository
import com.rocky.core.data.networking.post
import com.rocky.core.domain.AuthInfo
import com.rocky.core.domain.SessionStorage
import com.rocky.core.domain.util.DataError
import com.rocky.core.domain.util.EmptyResult
import com.rocky.core.domain.util.Result
import com.rocky.core.domain.util.asEmptyDataResult
import io.ktor.client.HttpClient

class DefaultAuthRepository(
    private val httpClient: HttpClient,
    private val sessionStorage: SessionStorage
) : AuthRepository {

    override suspend fun login(email: String, password: String): EmptyResult<DataError.Network> {
        val result = httpClient.post<LoginRequest, LoginResponse>(
            route = "/login",
            body = LoginRequest(email, password),
        )
        if (result is Result.Success) {
            sessionStorage.set(
                AuthInfo(
                    accessToken = result.data.accessToken,
                    refreshToken = result.data.refreshToken,
                    userId = result.data.userId
                )
            )
        }
        return result.asEmptyDataResult()
    }

    override suspend fun register(email: String, password: String): EmptyResult<DataError.Network> {
        return httpClient.post<RegisterRequest, Unit>(
            route = "/signup",
            body = RegisterRequest(email, password)
        )
    }

}