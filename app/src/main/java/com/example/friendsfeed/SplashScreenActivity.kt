package com.example.friendsfeed

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.friendsfeed.SharedPreference.SharedPrefManager
import androidx.lifecycle.Observer
import com.example.friendsfeed.auth.AuthViewModel
import com.example.friendsfeed.auth.AuthViewModelFactory
import com.example.friendsfeed.auth.LoginActivity
import com.example.friendsfeed.auth.data.db.entities.AccessToken
import com.example.friendsfeed.postpackage.HomeActivity
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

/**
 * @Project FriendsFeed
 * @Created_by Mayank Kumar on 11-04-2020 12:10 PM
 */
class SplashScreenActivity : AppCompatActivity(), KodeinAware {
    private var ms: Long = 0
    private val splashTime: Long = 2000
    private val splashActive = true
    private val paused = false

    // Implementing Kodein (Kotlin Dependency Injection) Framework.
    override val kodein by kodein()
    private val factory: AuthViewModelFactory by instance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

        // Enable FCM to generate Instance.
        //generateFCMInstanceID();
        // Getting extra data from Notification message.
        if (intent != null && intent.hasExtra("key1")) {
            for (key in intent.extras!!.keySet()) {
                Log.d(TAG, "onCreate: " + key + " Data: " + intent.extras!!.getString(key))
            }
        }

        val viewModel = ViewModelProvider(this, factory).get(AuthViewModel::class.java)
        var isLogedin = false
        viewModel.getSavedAccessToken().observe(this, Observer { accessToken ->
            if (accessToken != null) {

                isLogedin = true
            }
        })

        val myThread: Thread = object : Thread() {
            override fun run() {
                try {
                    while (splashActive && ms < splashTime) {
                        if (!paused) ms = ms + 100
                        sleep(100)
                    }
                } catch (e: Exception) {
                } finally {
                    if (isLogedin) {
                        Intent(this@SplashScreenActivity, HomeActivity::class.java).also {
                            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(it)
                        }
                    } else {
                        startActivity(Intent(this@SplashScreenActivity, LoginActivity::class.java))
                        finish()
                    }
                }
            }
        }
        myThread.start()
    }

    companion object {
        private const val TAG = "SplashScreenActivity"
    }
}