package com.example.friendsfeed.app

import android.app.Application
import com.example.friendsfeed.auth.AuthViewModelFactory
import com.example.friendsfeed.auth.data.db.AppDatabase
import com.example.friendsfeed.auth.data.network.MyApi
import com.example.friendsfeed.auth.data.network.NetworkConnectionInterceptor
import com.example.friendsfeed.auth.data.preferences.PreferenceProvider
import com.example.friendsfeed.auth.data.repositories.HomePostRepository
import com.example.friendsfeed.auth.data.repositories.ProfileRepository
import com.example.friendsfeed.auth.data.repositories.UserRepository
import com.example.friendsfeed.postpackage.allpost.AllPostFragment
import com.example.friendsfeed.postpackage.allpost.AllPostViewModelFactory
import com.example.friendsfeed.profile.ProfileViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

/**
 * @Project FriendsFeed
 * @Created_by Mayank Kumar on 17-09-2020 04:40 PM
 */
class FFeedApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@FFeedApplication))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { MyApi(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { PreferenceProvider(instance()) }
        bind() from singleton { UserRepository(instance(), instance(),instance()) }
        bind() from singleton { HomePostRepository(instance(), instance(),instance()) }
        bind() from singleton { ProfileRepository(instance(), instance()) }
        bind() from provider { AuthViewModelFactory(instance()) }
        bind() from provider { AllPostViewModelFactory(instance()) }
        bind() from provider { ProfileViewModelFactory(instance()) }

    }
}