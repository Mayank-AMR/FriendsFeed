package com.example.friendsfeed.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.friendsfeed.R

/**
 * @Project FriendsFeed
 * @Created_by Mayank Kumar on 23-09-2020 02:22 AM
 */

@BindingAdapter("image")
fun loadImage(view: ImageView, url: String?) {
    Glide.with(view)
            .load(url)
            //.centerCrop()
            .fitCenter()
            .placeholder(R.drawable.a18)
            .into(view)
}