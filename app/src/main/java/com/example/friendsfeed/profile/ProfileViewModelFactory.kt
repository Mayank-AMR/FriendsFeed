package com.example.friendsfeed.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.friendsfeed.auth.data.repositories.ProfileRepository

/**
 * @Project FriendsFeed
 * @Created_by Mayank Kumar on 27-09-2020 05:29 PM
 */

@Suppress("UNCHECKED_CAST")

class ProfileViewModelFactory(
        private val repository: ProfileRepository
) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProfileViewModel(repository) as T
    }

}