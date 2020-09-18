package com.example.friendsfeed.auth


/**
 * @Project FriendsFeed
 * @Created_by Mayank Kumar on 09-09-2020 05:19 PM
 */
interface AuthListener {
        fun onStarted()
        fun onSuccess(message: String)
        fun onFailure(message: String)

    }