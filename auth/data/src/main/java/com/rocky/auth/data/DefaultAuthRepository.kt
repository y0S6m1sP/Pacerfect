package com.rocky.auth.data

import com.rocky.auth.domain.AuthRepository
import com.rocky.core.data.networking.post
import com.rocky.core.domain.util.DataError
import com.rocky.core.domain.util.EmptyResult
import io.ktor.client.HttpClient

class DefaultAuthRepository(
    private val httpClient: HttpClient
): AuthRepository {

    override suspend fun register(email: String, password: String): EmptyResult<DataError.Network> {
        return httpClient.post<RegisterRequest, Unit>(
            route = "/signup",
            body = RegisterRequest(email, password)
        )
    }

}