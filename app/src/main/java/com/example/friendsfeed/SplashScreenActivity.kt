package com.example.friendsfeed

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.friendsfeed.SharedPreference.SharedPrefManager
import com.example.friendsfeed.SplashScreenActivity
import com.example.friendsfeed.auth.LoginActivity
import com.example.friendsfeed.postpackage.HomeActivity

/**
 * @Project FriendsFeed
 * @Created_by Mayank Kumar on 11-04-2020 12:10 PM
 */
class SplashScreenActivity : AppCompatActivity() {
    private var ms: Long = 0
    private val splashTime: Long = 2000
    private val splashActive = true
    private val paused = false
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
        val myThread: Thread = object : Thread() {
            override fun run() {
                try {
                    while (splashActive && ms < splashTime) {
                        if (!paused) ms = ms + 100
                        sleep(100)
                    }
                } catch (e: Exception) {
                } finally {
                    Log.d(TAG, "run: " + SharedPrefManager.getInstance(this@SplashScreenActivity).isSignIn)
                    if (SharedPrefManager.getInstance(this@SplashScreenActivity).isSignIn) {
                        startActivity(Intent(this@SplashScreenActivity, HomeActivity::class.java))
                        finish()
                    } else {
                        startActivity(Intent(this@SplashScreenActivity, LoginActivity::class.java))
                        finish()
                    }
                    //-----------------------------------------------------------------------------
                    //------------------------------------------------------------------------------
                }
            }
        }
        myThread.start()
    }

    companion object {
        private const val TAG = "SplashScreenActivity"
    }
}