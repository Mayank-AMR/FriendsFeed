package com.example.friendsfeed.postpackage.allpost

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.friendsfeed.auth.data.repositories.HomePostRepository
import com.example.friendsfeed.auth.data.repositories.UserRepository

/**
 * @Project FriendsFeed
 * @Created_by Mayank Kumar on 15-09-2020 11:19 AM
 */
@Suppress("UNCHECKED_CAST")
class AllPostViewModelFactory(
        private val repository: HomePostRepository
) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AllPostViewModel(repository) as T
    }
}