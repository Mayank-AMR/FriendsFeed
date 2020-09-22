package com.example.friendsfeed.auth.data.network.responses

import com.example.friendsfeed.auth.data.db.entities.HomePosts

/**
 * @Project FriendsFeed
 * @Created_by Mayank Kumar on 19-09-2020 09:21 AM
 */
data class HomePostResponse(
        val status: Int,
        val message: List<HomePosts>
)



