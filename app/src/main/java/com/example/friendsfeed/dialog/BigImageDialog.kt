package com.example.friendsfeed.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.example.friendsfeed.R
import kotlinx.android.synthetic.main.dialog_big_image.view.*

/**
 * @Project FriendsFeed
 * @Created_by Mayank Kumar on 04-08-2020 06:33 PM
 */

class BigImageDialog(): DialogFragment() {
    private var imageUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            imageUrl =  it.getString("url")
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.dialog_big_image, container, false)
        this.dialog!!.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        Glide.with(this).load(imageUrl).placeholder(R.drawable.a18).into(v.bigImageView)

        return v
    }

    override fun onStart() {
        super.onStart()

        val dialog = dialog
        if (dialog != null) {
            dialog.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(imageUrl: String) =
                BigImageDialog().apply {
                    arguments = Bundle().apply {
                        putString("url", imageUrl)
                    }
                }
    }
}