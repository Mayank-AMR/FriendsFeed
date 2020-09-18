package com.example.friendsfeed.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.friendsfeed.auth.data.repositories.UserRepository

/**
 * @Project FriendsFeed
 * @Created_by Mayank Kumar on 15-09-2020 11:19 AM
 */
@Suppress("UNCHECKED_CAST")
class AuthViewModelFactory(
        private val repository: UserRepository
) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AuthViewModel(repository) as T
    }

}