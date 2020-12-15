package com.example.friendsfeed.postpackage.allpost

import androidx.lifecycle.ViewModel
import com.example.friendsfeed.auth.data.preferences.PreferenceProvider
import com.example.friendsfeed.auth.data.repositories.HomePostRepository
import com.example.friendsfeed.utils.lazyDeferred

class AllPostViewModel(
        repository: HomePostRepository,
        preference: PreferenceProvider

) : ViewModel() {
    private val acceptType = "application/json"

    val posts by lazyDeferred {
        //repository.getPosts(acceptType, getAuthorization())

        preference.getUserAccessToken()?.let { repository.getPosts(acceptType, it) }
    }

}