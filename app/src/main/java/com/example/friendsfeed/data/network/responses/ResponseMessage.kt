package com.example.friendsfeed.data.network.responses


/**
 * @Project FriendsFeed
 * @Created_by Mayank Kumar on 13-09-2020 01:54 PM
 */
data class ResponseMessage(
        val active: Int?,
        val token_type: String?,
        val access_token: String?,
        val user : List<User>
)