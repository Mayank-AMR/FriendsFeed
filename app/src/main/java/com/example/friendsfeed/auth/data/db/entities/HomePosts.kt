package com.example.friendsfeed.auth.data.db.entities

import androidx.room.*
import com.example.friendsfeed.auth.data.network.responses.HomeUser

/**
 * @Project FriendsFeed
 * @Created_by Mayank Kumar on 20-09-2020 07:08 PM
 */
@Entity
data class HomePosts (
        @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "rowid")val id: Int,
        val user_id: Int,
        @Embedded var user: HomeUser,
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

        //@Embedded @ColumnInfo(name = "home_user_id")var user: HomeUser
        //val userID:Int = user.id
)