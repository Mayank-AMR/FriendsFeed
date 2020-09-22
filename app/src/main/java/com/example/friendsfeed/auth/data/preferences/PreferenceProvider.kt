package com.example.friendsfeed.auth.data.preferences

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

/**
 * @Project FriendsFeed
 * @Created_by Mayank Kumar on 21-09-2020 05:30 PM
 */

private const val KEY_SAVED_AT = "key_saved_at"

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
}