package com.example.friendsfeed.auth.data.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

/**
 * @Project FriendsFeed
 * @Created_by Mayank Kumar on 21-09-2020 05:30 PM
 */

private const val KEY_SAVED_AT = "key_saved_at"
private const val KEY_ACCESS_TOKEN = "key_access_token"
private const val KEY_EMAIL_VERIFICATION_STATUS = "key_email_verification_status"


class PreferenceProvider(
        context: Context
) {

    private val appContext = context.applicationContext

    private val preferences: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(appContext)

    fun saveLastSavedAt(savedAt: String) {
        preferences.edit().putString(KEY_SAVED_AT, savedAt)
                .apply()
    }

    fun getLastSavedAt(): String? {
        return preferences.getString(KEY_SAVED_AT, null)
    }

    fun saveUserAccessToken(tokenType: String, token: String) {
        preferences.edit().putString(KEY_ACCESS_TOKEN, "$tokenType $token")
                .apply()
    }

    fun getUserAccessToken() = preferences.getString(KEY_ACCESS_TOKEN, null)

    fun saveEmailVerificationStatus(status: Int) {
        preferences.edit().putInt(KEY_EMAIL_VERIFICATION_STATUS, status)
                .apply()
    }

    fun getEmailVerificationStatus() = preferences.getInt(KEY_EMAIL_VERIFICATION_STATUS, -1)
}