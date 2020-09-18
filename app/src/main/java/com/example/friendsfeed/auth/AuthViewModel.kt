package com.example.friendsfeed.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.friendsfeed.auth.data.db.entities.AccessToken
import com.example.friendsfeed.utils.Coroutine
import com.example.friendsfeed.auth.data.repositories.UserRepository
import com.example.friendsfeed.utils.ApiException
import com.example.friendsfeed.utils.NoInternetException

/**
 * @Project FriendsFeed
 * @Created_by Mayank Kumar on 09-09-2020 05:17 PM
 */
class AuthViewModel(
        // Here I'm using constructor injection for UserRepository
        private val repository: UserRepository
) : ViewModel() {

    var name: String? = null
    var email: String? = null
    var password: String? = null
    var confirmPassword: String? = null
    var dob: String? = null
    var gender: String? = null

    var authListener: AuthListener? = null

    fun getSavedAccessToken() = repository.getToken()

    fun onLoginButtonClick(view: View) {
        authListener?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("Invalid email or password")
            return
        }

        Coroutine.main {
            try {
                val authResponse = repository.userLogin(email!!, password!!)
                authResponse.message[0].let {
                    if (it.token_type.equals("Bearer"))
                        authListener?.onSuccess("Login Successful")
                    repository.saveToken(AccessToken(it.active, it.token_type, it.access_token))
                    return@main
                }
            } catch (e: ApiException) {
                authListener?.onFailure(e.message!!)
            } catch (e: NoInternetException) {
                authListener?.onFailure(e.message!!)
            }
        }
    }

    fun onSignupButtonClick(view: View) {
        authListener?.onStarted()

        if (name.isNullOrEmpty()) {
            authListener?.onFailure("Please enter name")
            return
        }
        if (email.isNullOrEmpty()) {
            authListener?.onFailure("Please enter email")
            return
        }
        if (password.isNullOrEmpty()) {
            authListener?.onFailure("Please enter password")
            return
        }
        if (password != confirmPassword) {
            authListener?.onFailure("Password did not match")
            return
        }

        if (dob.isNullOrEmpty()) {
            authListener?.onFailure("Enter your Birth")
            return
        }
        if (gender.isNullOrEmpty()) {
            authListener?.onFailure("Select your gender")
            return
        }

        Coroutine.main {
            try {
                val registrationResponse = repository.userSignup(name!!, email!!, password!!, gender!!, dob!!)
                registrationResponse.message[0].let {
                    authListener?.onSuccess(it.msg!!)
                    //TODO("Need to solve by dependency injection")
                    //repository.saveToken(AccessToken(it[0].active, it[0].token_type, it[0].access_token))
                    return@main
                }
            } catch (e: ApiException) {
                authListener?.onFailure(e.message!!)
            } catch (e: NoInternetException) {
                authListener?.onFailure(e.message!!)
            }
        }
    }

}