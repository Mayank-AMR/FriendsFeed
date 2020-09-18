package com.example.friendsfeed.auth.data.network.responses

/**
 * @Project FriendsFeed
 * @Created_by Mayank Kumar on 13-09-2020 01:56 PM
 */
data class User(
        val id: Int?,
        val name: String?,
        val username: String?,
        val email: String?,
        val dob: String?,
        val gender: String?,
        val profileImage: String?,
        val following: Int?,
        val followers: Int?,
        val bio: String?,
        val created_at: String?
)