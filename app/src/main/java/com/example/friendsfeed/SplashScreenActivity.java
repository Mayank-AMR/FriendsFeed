package com.example.friendsfeed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import com.example.friendsfeed.SharedPreference.SharedPrefManager;
import com.example.friendsfeed.application.FriendsFeedApplication;

/**
 * @Project FriendsFeed
 * @Created_by Mayank Kumar on 11-04-2020 12:10 PM
 */

public class SplashScreenActivity extends AppCompatActivity {
    private static final String TAG = "SplashScreenActivity";
    private long ms = 0;
    private long splashTime = 2000;
    private boolean splashActive = true;
    private boolean paused = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);


        Thread myThread = new Thread() {
            public void run() {
                try {
                    while (splashActive && ms < splashTime) {
                        if (!paused)
                            ms = ms + 100;
                        sleep(100);
                    }
                } catch (Exception e) {

                } finally {
                    Log.d(TAG, "run: " + SharedPrefManager.getInstance(SplashScreenActivity.this).isSignIn());

                    if (SharedPrefManager.getInstance(SplashScreenActivity.this).isSignIn()) {
                        startActivity(new Intent(SplashScreenActivity.this, HomeActivity.class));
                        finish();
                    } else {
                        startActivity(new Intent(SplashScreenActivity.this, SignInActivity.class));
                        finish();
                    }
                }
            }
        };
        myThread.start();

    }
}
