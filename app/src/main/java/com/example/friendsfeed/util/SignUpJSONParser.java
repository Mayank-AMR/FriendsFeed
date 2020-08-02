package com.example.friendsfeed.util;

import org.json.JSONObject;

/**
 * @Project FriendsFeed
 * @Created_by Mayank Kumar on 24-07-2020 03:18 PM
 */
public class SignUpJSONParser {
    private final static String STATUS = "status";
    private final static String MESSAGE = "message";

    public static boolean signUpResultHandle(JSONObject response) {
        int status = response.optInt(STATUS);
        String message = response.optString(MESSAGE);
        if (status == 200 && message.equals("Registration successful")) {
            return true;
        } else return false;

    }


}
