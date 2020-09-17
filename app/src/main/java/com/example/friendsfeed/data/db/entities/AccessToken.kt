package com.example.friendsfeed.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @Project FriendsFeed
 * @Created_by Mayank Kumar on 15-09-2020 12:30 PM
 */
const val CURRENT_USER_ID = 0

@Entity
data class AccessToken(
        var active: Int? = null,
        var token_type: String? = null,
        var access_token: String? = null
) {
    @PrimaryKey(autoGenerate = false)
    var uid: Int? = CURRENT_USER_ID
}