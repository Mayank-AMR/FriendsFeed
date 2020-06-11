package com.example.friendsfeed.util;


import android.util.Log;

import com.example.friendsfeed.SharedPreference.SavedLoginAuthentication;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Objects;

/**
 * @Project FriendsFeed
 * @Created_by Mayank Kumar on 11-06-2020 04:36 PM
 */
public class SignInJSONParser {
    private static final String TAG = "SignInJSONParser";

    public SavedLoginAuthentication signInJsonParse(JSONObject response) {
        int status = response.optInt("status");
        if (status == 200) {
            JSONArray message = response.optJSONArray("message");
            JSONObject detail = Objects.requireNonNull(message).optJSONObject(0);

            String id = String.valueOf(detail.optInt("id"));
            String name = detail.optString("name");
            String username = detail.optString("username");
            String email = detail.optString("email");
            String email_verified_at = detail.optString("email_verified_at");
            String profileImage = detail.optString("profileImage");
            String profileCover = detail.optString("profileCover");
            String following = detail.optString("following");
            String followers = detail.optString("followers");
            String bio = detail.optString("bio");
            String country = detail.optString("country");
            String website = detail.optString("website");
            String created_at = detail.optString("created_at");
            String updated_at = detail.optString("updated_at");

            return new SavedLoginAuthentication(id, name, username, email, email_verified_at,
                    profileImage, profileCover, following, followers, bio, country, website,
                    created_at, updated_at);
        } else {
            Log.d(TAG, "signInJsonParse: " + response.optString("message"));
        }
        return null;
    }
}
