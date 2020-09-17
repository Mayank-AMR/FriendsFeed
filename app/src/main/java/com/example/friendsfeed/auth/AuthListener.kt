package com.example.friendsfeed.auth

import com.example.friendsfeed.data.network.responses.ResponseMessage

/**
 * @Project FriendsFeed
 * @Created_by Mayank Kumar on 09-09-2020 05:19 PM
 */
interface AuthListener {
        fun onStarted()
        fun onSuccess(response: List<ResponseMessage>)
        fun onFailure(message: String)

    }