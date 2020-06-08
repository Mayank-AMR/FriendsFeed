package com.example.friendsfeed.application;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.appcompat.app.AppCompatDelegate;

/**
 * @Project FriendsFeed
 * @Created_by Mayank Kumar on 11-04-2020 12:00 PM
 */
public class FriendsFeedApplication extends Application {
    private static final String TAG = "FriendsFeedApplication";
    private static FriendsFeedApplication instance;
    private static Context appContext;

    public static FriendsFeedApplication getInstance() {
        return instance;
    }

    public static Context getAppContext() {
        return appContext;
    }

    public void setAppContext(Context mAppContext) {
        Log.d(TAG, "setAppContext: ");
        this.appContext = mAppContext;
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate: ");
        super.onCreate();
        instance = this;

        this.setAppContext(getApplicationContext());
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
}
