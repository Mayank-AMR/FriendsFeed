package com.example.friendsfeed.profile

import androidx.lifecycle.ViewModel
import com.example.friendsfeed.auth.data.repositories.ProfileRepository
import com.example.friendsfeed.utils.lazyDeferred

class ProfileViewModel(
        profileRepository: ProfileRepository
) : ViewModel() {


    val profile by lazyDeferred {
        profileRepository.getProfile()

    }
}