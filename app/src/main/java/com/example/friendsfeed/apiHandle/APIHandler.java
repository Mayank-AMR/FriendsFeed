package com.example.friendsfeed.apiHandle;

import android.net.Uri;
import android.text.format.DateFormat;

import java.util.Date;

/**
 * @Project FriendsFeed
 * @Created_by Mayank Kumar on 24-07-2020 02:23 PM
 */
public class APIHandler {
    private static final String TAG = "APIHandler";


    public static final String SIGN_UP_BASE_URL = "https://friendsfeed.herokuapp.com/api/users/register";
    public static final String SIGN_IN_BASE_URL = "https://diready.co/api/login";

    private static final String NAME_PARAM_SIGNUP = "name";
    private static final String EMAIL_PARAM_SIGNUP = "email";
    private static final String PASSWORD_PARAM_SIGNUP = "password";
    private static final String DOB_DAY_PARAM_SIGNUP = "date";
    private static final String DOB_MONTH_PARAM_SIGNUP = "month";
    private static final String DOB_YEAR_PARAM_SIGNUP = "year";
    private static final String GENDER_PARAM_SIGNUP = "gender";

    private static final String EMAIL_PARAM_SIGNIN ="email";
    private static final String PASSWORD_PARAM_SIGNIN ="password";

    /* _____________*** This code deprecated because it is for diredy.co server ***_________________

    // SignUp URL create...
    public static String signUpUriBuilder(String fullName, String email, String password, Date date, String gender) {
        Uri signUpURI = Uri.parse(SIGN_UP_BASE_URL).buildUpon()
                .appendQueryParameter(NAME_PARAM_SIGNUP, fullName)
                .appendQueryParameter(EMAIL_PARAM_SIGNUP, email)
                .appendQueryParameter(PASSWORD_PARAM_SIGNUP, password)
                .appendQueryParameter(DOB_DAY_PARAM_SIGNUP, (String) DateFormat.format("dd", date))
                .appendQueryParameter(DOB_MONTH_PARAM_SIGNUP, (String) DateFormat.format("MM", date))
                .appendQueryParameter(DOB_YEAR_PARAM_SIGNUP, (String) DateFormat.format("yyyy", date))
                .appendQueryParameter(GENDER_PARAM_SIGNUP, gender)
                .build();
        return signUpURI.toString();
    }
    ______________________________________________________________________________________________*/

    //SignIn URL create
    public static String signInUriBuilder(String email, String password){
        Uri signInURI = Uri.parse(SIGN_IN_BASE_URL).buildUpon()
                .appendQueryParameter(EMAIL_PARAM_SIGNIN, email)
                .appendQueryParameter(PASSWORD_PARAM_SIGNIN, password)
                .build();
        return signInURI.toString();



//        Uri signIn = Uri.parse("https://friendsfeed.herokuapp.com/api/users/login").buildUpon()
    }

    public static String registerUriBuilder(){
        Uri uri = Uri.parse(SIGN_UP_BASE_URL).buildUpon().build();
        return uri.toString();
    }



}
