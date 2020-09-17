package com.example.friendsfeed.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.friendsfeed.HomeActivity
import com.example.friendsfeed.R
import com.example.friendsfeed.data.db.AppDatabase
import com.example.friendsfeed.data.db.entities.AccessToken
import com.example.friendsfeed.data.network.MyApi
import com.example.friendsfeed.data.network.NetworkConnectionInterceptor
import com.example.friendsfeed.data.network.responses.ResponseMessage
import com.example.friendsfeed.data.repositories.UserRepository
import com.example.friendsfeed.databinding.ActivityLoginBinding
import com.example.friendsfeed.utils.*
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), AuthListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // TODO("Need to solve these instance by dependency injection ")
        val networkConnectionInterceptor = NetworkConnectionInterceptor(this)
        val api = MyApi(networkConnectionInterceptor)
        val db = AppDatabase(this)
        val repository = UserRepository(api,db)
        val factory = AuthViewModelFactory(repository)

        val binding: ActivityLoginBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_login)
        var viewModel = ViewModelProvider(this, factory).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel
        viewModel.authListener = this

        viewModel.getSavedAccessToken().observe(this, Observer {accessToken ->
            if (accessToken!=null){
                Intent(this,HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK // no other activity in stack bz of Flag
                    startActivity(it)
                }
            }
        })


    }

    override fun onStarted() {
        progress_bar_login.show()
        log("Login Started..")
    }

    override fun onSuccess(responseMessages: List<ResponseMessage>) {
        progress_bar_login.hide()
        log("\nName is -> ${responseMessages?.get(0).user[0].name} " +
                "\nAccess Code is -> ${responseMessages?.get(0).access_token} ")

//        root_layout.snackbar("\nName is -> ${responseMessages?.get(0).user[0].name} " +
//                "\nAccess Code is -> ${responseMessages?.get(0).access_token} ")

    }

    override fun onFailure(message: String) {
        progress_bar_login.hide()
        log(message)
        //toast(message)
        root_layout.snackbar(message)
    }

}