package com.example.friendsfeed.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.friendsfeed.data.db.entities.AccessToken
import com.example.friendsfeed.utils.Coroutine
import com.example.friendsfeed.data.repositories.UserRepository
import com.example.friendsfeed.utils.ApiException
import com.example.friendsfeed.utils.NoInternetException
import java.io.IOException

/**
 * @Project FriendsFeed
 * @Created_by Mayank Kumar on 09-09-2020 05:17 PM
 */
class AuthViewModel(
        // Here I'm using constructor injection for UserRepository
        private val repository: UserRepository
) : ViewModel() {

    var email: String? = null
    var password: String? = null

    var authListener: AuthListener? = null

    fun getSavedAccessToken() = repository. getToken()

    fun onLoginButtonClick(view: View) {
        authListener?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("Invalid email or password")
            return
        }

        Coroutine.main {
            try {
                val authResponse = repository.userLogin(email!!, password!!)
                authResponse.message.let {
                    authListener?.onSuccess(it)
                    //TODO("Need to solve by dependency injection")
                    repository.saveToken(AccessToken(it[0].active,it[0].token_type,it[0].access_token))
                    return@main
                }
            } catch (e: ApiException) {
                authListener?.onFailure(e.message!!)
            }catch (e: NoInternetException) {
                authListener?.onFailure(e.message!!)
            }
        }
    }

}