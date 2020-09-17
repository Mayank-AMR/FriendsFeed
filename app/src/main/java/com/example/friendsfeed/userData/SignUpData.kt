package com.example.friendsfeed.userData

import org.json.JSONException
import org.json.JSONObject
import java.util.*

/**
 * @Project FriendsFeed
 * @Created_by Mayank Kumar on 26-08-2020 11:25 AM
 */
public class SignUpData(fullname: String, email: String, password: String, gender: String, dob: Date) {
    private var mFullName: String? = null
    private var mEmail: String? = null
    private var mPassword: String? = null
    private var mGender: String? = null
    private var mDoB: Date? = null

    private val KEY_NAME = "name"
    private val KEY_EMAIL = "email"
    private val KEY_PASSWORD = "password"
    private val KEY_GENDER = "gender"
    private val KEY_DOB = "dob"


    init {
        mFullName = fullname
        mEmail = email
        mPassword = password
        mGender = gender
        mDoB = dob
    }

    public fun getRegisterDataBody(): JSONObject? {
        // Putting user data in JSONObject .........................................................
        val registerBody = JSONObject()
        try {
            registerBody.put(KEY_NAME, mFullName)
            registerBody.put(KEY_EMAIL, mEmail)
            registerBody.put(KEY_PASSWORD, mPassword)
            registerBody.put(KEY_GENDER, mGender)
            registerBody.put(KEY_DOB, mDoB)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return registerBody
    }


}