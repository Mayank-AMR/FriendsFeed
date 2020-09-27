package com.example.friendsfeed.auth.data.repositories

import com.example.friendsfeed.auth.data.db.AppDatabase
import com.example.friendsfeed.auth.data.db.entities.AccessToken
import com.example.friendsfeed.auth.data.network.MyApi
import com.example.friendsfeed.auth.data.network.SafeApiRequest
import com.example.friendsfeed.auth.data.network.responses.AuthResponse
import com.example.friendsfeed.auth.data.network.responses.RegistrationResponse
import com.example.friendsfeed.auth.data.network.responses.RegistrationResponseMessage
import com.example.friendsfeed.auth.data.preferences.PreferenceProvider

/**
 * @Project Sample Chat
 * @Created_by Mayank Kumar on 08-09-2020 08:34 AM
 */
class UserRepository(
        private val api: MyApi,
        private val db: AppDatabase,
        private val tokenPrefs: PreferenceProvider

) : SafeApiRequest() {
    private val acceptType = "application/json"

    suspend fun userLogin(email: String, password: String): AuthResponse {

        return apiRequest { api.userLogin(acceptType, email, password) }
    }

    suspend fun userSignup(name: String, email: String, password: String, gender: String, dob: String)
            : RegistrationResponse {

        return apiRequest { api.userSignup(acceptType, name, email, password, gender, dob) }
    }

    suspend fun saveToken(accessToken: AccessToken) = db.getTokenDao().upsert(accessToken)

    fun getToken() = db.getTokenDao().getAccessToken()


    fun saveTokenInPrefs(tokenType: String, token: String) {
        tokenPrefs.saveUserAccessToken(tokenType, token)

    }

    fun getTokenFromPrefs() = tokenPrefs.getUserAccessToken()

    fun saveEmailVerStatusInPrefs(status: Int) {
        tokenPrefs.saveEmailVerificationStatus(status)
    }

    fun getEmailVerStatusFromPrefs() = tokenPrefs.getEmailVerificationStatus()

}