package com.example.friendsfeed.data.repositories

import com.example.friendsfeed.data.db.AppDatabase
import com.example.friendsfeed.data.db.entities.AccessToken
import com.example.friendsfeed.data.network.MyApi
import com.example.friendsfeed.data.network.SafeApiRequest
import com.example.friendsfeed.data.network.responses.AuthResponse

/**
 * @Project Sample Chat
 * @Created_by Mayank Kumar on 08-09-2020 08:34 AM
 */
class UserRepository(
        private val api: MyApi,
        private val db: AppDatabase
) : SafeApiRequest() {
    suspend fun userLogin(email: String, password: String): AuthResponse {

        return apiRequest { api.userLogin("application/json", email, password) }
    }

    suspend fun saveToken(accessToken: AccessToken) = db.getTokenDao().upsert(accessToken)

    fun getToken() = db.getTokenDao().getAccessToken()
}