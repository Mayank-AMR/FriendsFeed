package com.example.friendsfeed.SharedPreference;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.friendsfeed.SignInActivity;

/**
 * @Project FriendsFeed
 * @Created_by Mayank Kumar on 11-04-2020 12:15 PM
 */

//TODO:  here for this class we are using a singleton pattern
public class SharedPrefManager {
    private static final String SHARED_PREF_NAME = "authenticationsharedpreference";

    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_USER_FULL_NAME = "full_name";
    private static final String KEY_USER_NAME = "user_name";
    private static final String KEY_USER_EMAIL = "user_email";
    private static final String KEY_EMAIL_VERIFIED = "email_verified";
    private static final String KEY_USER_PIC_URL = "user_pic_url";
    private static final String KEY_USER_PROFILE_COVER = "user_profile_cover";
    private static final String KEY_USER_BIO = "user_bio";
    private static final String KEY_USER_COUNTRY = "user_country";
    private static final String KEY_USER_WEBSITE = "user_website";
    private static final String KEY_USER_CREATED_AT = "user_created_at";
    private static final String KEY_USER_UPDATED_AT = "updated_at";
    private static final String KEY_USER_FOLLOWING_TO = "user_following_to";
    private static final String KEY_USER_FOLLOWED_BY = "user_followed_by";

    /**
     * These are live data variables but also saved here
     * private String updated_at;
     * private String following;
     * private String followers;
     */
    private static SharedPrefManager mInstance;
    private static Context mCtx;

    private SharedPrefManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    //TODO: method to let the user login this method will store the user data in shared preferences
    public void putSignInStatus(SavedLoginAuthentication savedLoginAuthentication) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USER_ID, savedLoginAuthentication.getUserId());
        editor.putString(KEY_USER_NAME, savedLoginAuthentication.getUserName());
        editor.putString(KEY_USER_FULL_NAME, savedLoginAuthentication.getFullName());
        editor.putString(KEY_USER_EMAIL, savedLoginAuthentication.getUserEmail());
        editor.putString(KEY_EMAIL_VERIFIED, savedLoginAuthentication.getEmail_verified_at());
        editor.putString(KEY_USER_PIC_URL, savedLoginAuthentication.getPicURL());
        editor.putString(KEY_USER_PROFILE_COVER, savedLoginAuthentication.getProfileCover());
        editor.putString(KEY_USER_BIO, savedLoginAuthentication.getBio());
        editor.putString(KEY_USER_COUNTRY, savedLoginAuthentication.getCountry());
        editor.putString(KEY_USER_WEBSITE, savedLoginAuthentication.getWebsite());
        editor.putString(KEY_USER_CREATED_AT, savedLoginAuthentication.getCreated_at());
        editor.putString(KEY_USER_UPDATED_AT, savedLoginAuthentication.getUpdated_at());
        editor.putString(KEY_USER_FOLLOWING_TO, savedLoginAuthentication.getFollowing());
        editor.putString(KEY_USER_FOLLOWED_BY, savedLoginAuthentication.getFollowers());
        editor.apply();
    }

    //TODO: method will checker whether user is already logged in or not
    public boolean isSignIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_ID, null) != null;
    }

    //TODO:  method will give the logged in user
    public SavedLoginAuthentication getLoginAuthentication() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new SavedLoginAuthentication(
                sharedPreferences.getString(KEY_USER_ID ,"user_id"),
                sharedPreferences.getString(KEY_USER_FULL_NAME ,"full_name"),
                sharedPreferences.getString(KEY_USER_NAME ,"user_name"),
                sharedPreferences.getString(KEY_USER_EMAIL ,"user_email"),
                sharedPreferences.getString(KEY_EMAIL_VERIFIED ,"email_verified"),
                sharedPreferences.getString(KEY_USER_PIC_URL ,"user_pic_url"),
                sharedPreferences.getString(KEY_USER_PROFILE_COVER ,"user_profile_cover"),
                sharedPreferences.getString(KEY_USER_BIO ,"user_bio"),
                sharedPreferences.getString(KEY_USER_COUNTRY ,"user_country"),
                sharedPreferences.getString(KEY_USER_WEBSITE ,"user_website"),
                sharedPreferences.getString(KEY_USER_CREATED_AT ,"user_created_at"),
                sharedPreferences.getString(KEY_USER_UPDATED_AT ,"updated_at"),
                sharedPreferences.getString(KEY_USER_FOLLOWING_TO ,"user_following_to"),
                sharedPreferences.getString(KEY_USER_FOLLOWED_BY ,"user_followed_by")
                );
    }

    //TODO:  method will logout the user
    public void signOut() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        mCtx.startActivity(new Intent(mCtx, SignInActivity.class));
    }
}