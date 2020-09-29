package com.example.friendsfeed.profile.response

data class Message(
    val active: String?,
    val bio: String?,
    val created_at: String?,
    val dob: String?,
    val email: String?,
    val follow: Int?,
    val followers: Int?,
    val following: Int?,
    val gender: String?,
    val id: Int?,
    val name: String?,
    val profileImage: String?,
    val username: String?
)