package com.example.friendsfeed.utils

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

/**
 * @Project FriendsFeed
 * @Created_by Mayank Kumar on 07-09-2020 11:01 PM
 */

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
fun ProgressBar.show(){
   visibility = View.VISIBLE
}

fun ProgressBar.hide(){
    visibility = View.GONE
}
fun Context.log(message: String){
    Log.d(TAG, "log: $message")
}

fun View.snackbar(message:String){
    Snackbar.make(this,message,Snackbar.LENGTH_LONG).also {snackbar ->
        snackbar.setAction("OK"){
            snackbar.dismiss()
        }
    }.show()
}