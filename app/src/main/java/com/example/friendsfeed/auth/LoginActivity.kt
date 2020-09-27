package com.example.friendsfeed.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.friendsfeed.R
import com.example.friendsfeed.databinding.ActivityLoginBinding
import com.example.friendsfeed.postpackage.HomeActivity
import com.example.friendsfeed.utils.hide
import com.example.friendsfeed.utils.log
import com.example.friendsfeed.utils.show
import com.example.friendsfeed.utils.snackbar
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class LoginActivity : AppCompatActivity(), AuthListener, KodeinAware {

    // Implementing Kodein (Kotlin Dependency Injection) Framework.
    override val kodein by kodein()
    private val factory: AuthViewModelFactory by instance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val binding: ActivityLoginBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_login)
        var viewModel = ViewModelProvider(this, factory).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel
        viewModel.authListener = this

        viewModel.getSavedAccessToken().observe(this, Observer { accessToken ->
            if (accessToken != null) {
                Intent(this, HomeActivity::class.java).also {
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

    override fun onSuccess(message: String) {
        progress_bar_login.hide()
        log(message)
    }

    override fun onFailure(message: String) {
        progress_bar_login.hide()
        log(message)
        root_layout.snackbar(message)
    }

}