package com.example.friendsfeed.postpackage.allpost

import com.bumptech.glide.Glide
import com.example.friendsfeed.R
import com.example.friendsfeed.auth.data.db.entities.HomePosts
import com.example.friendsfeed.databinding.PostViewBinding
import com.xwray.groupie.databinding.BindableItem

/**
 * @Project FriendsFeed
 * @Created_by Mayank Kumar on 23-09-2020 01:04 AM
 */
class HomePostsItem(
        private val homePosts: HomePosts

) : BindableItem<PostViewBinding>() {

    override fun getLayout() = R.layout.post_view

    override fun bind(viewBinding: PostViewBinding, position: Int) {
        //Glide.with(PostViewB).load(image.image).into(PostViewBinding.itemView.feed_single_photo_photo)
        viewBinding.setPost(homePosts)
    }

//    override fun bind(viewHolder: ViewHolder, position: Int) {
//        Glide.with(NOT_SURE_WHAT_TO_PLACE_HERE).load(image.image).into(viewHolder.itemView.feed_single_photo_photo)
//    }



}