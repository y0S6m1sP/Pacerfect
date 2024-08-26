package com.rocky.auth.domain

import com.rocky.core.domain.util.DataError
import com.rocky.core.domain.util.EmptyResult

interface AuthRepository {
    suspend fun login(email: String, password: String): EmptyResult<DataError.Network>
    suspend fun register(email: String, password: String): EmptyResult<DataError.Network>
}