package com.example.friendsfeed.auth.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @Project FriendsFeed
 * @Created_by Mayank Kumar on 20-09-2020 07:08 PM
 */
@Entity
data class HomePosts (
        @PrimaryKey(autoGenerate = false) val id: Int,
        val user_id: Int,
        val post: String?,
        val post_image1: String?,
        val post_image2: String?,
        val post_image3: String?,
        val post_image4: String?,
        val post_image5: String?,
        val likes_count: Int,
        val comments_count: Int,
        val liked: Int,
        val created_at: String,
        val updated_at: String
        //var user: List<HomeUser>
)