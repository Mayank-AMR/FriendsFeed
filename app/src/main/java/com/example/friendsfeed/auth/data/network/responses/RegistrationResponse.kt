package com.example.friendsfeed.auth.data.network.responses


/**
 * @Project FriendsFeed
 * @Created_by Mayank Kumar on 17-09-2020 06:04 PM
 */


data class RegistrationResponse(
        var status: Int? = null,
        var message: List<RegistrationResponseMessage>
)


data class RegistrationResponseMessage(
        var user_id: Int? = null,
        var email: String? = null,
        var msg: String? = null
)