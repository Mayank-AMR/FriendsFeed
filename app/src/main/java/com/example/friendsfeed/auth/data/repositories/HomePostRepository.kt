package com.example.friendsfeed.auth.data.repositories

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.friendsfeed.auth.data.db.AppDatabase
import com.example.friendsfeed.auth.data.db.entities.HomePosts
import com.example.friendsfeed.auth.data.network.MyApi
import com.example.friendsfeed.auth.data.network.SafeApiRequest
import com.example.friendsfeed.auth.data.preferences.PreferenceProvider
import com.example.friendsfeed.utils.Coroutine
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.temporal.ChronoUnit
import java.util.*

/**
 * @Project FriendsFeed
 * @Created_by Mayank Kumar on 20-09-2020 04:07 PM
 */
class HomePostRepository(

        private val api: MyApi,
        private val db: AppDatabase,
        private val prefs: PreferenceProvider

) : SafeApiRequest() {

    private val posts = MutableLiveData<List<HomePosts>>()

    init {
        // observeForever observes if any change occurs in posts then savePosts() save it in local db.
        posts.observeForever {
            savePosts(it)
        }
    }


    // getPosts() returns posts to AllPostViewModel from local db.
    suspend fun getPosts(acceptType: String, authorization: String): LiveData<List<HomePosts>> {

        return withContext(Dispatchers.IO) {
            fetchPosts(acceptType, authorization)
            db.getHomePOstDao().getHomePosts()
        }
    }


    // fetchPosts() fetch posts from backend API and put in 'posts' live data.
    private suspend fun fetchPosts(acceptType: String, authorization: String) {
        val lastSavedAt = prefs.getLastSavedAt()

        if (lastSavedAt == null || isFetchNeeded(lastSavedAt)) {
            val response = apiRequest { api.getHomePost(acceptType, authorization) }
            posts.postValue(response.message)
        }
    }


    // isFetchNeeded() contain logic when to fetch the post from backend.
    private fun isFetchNeeded(lastSavedAt: String): Boolean {
        // Later I will put logic about when fetch needed.

        //val formet =  SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        //val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm")
        // val formattedDate = formatter.format(parser.parse(lastSavedAt))
        return true
    }


    // savePosts() saves posts in local db.
    @SuppressLint("SimpleDateFormat")
    private fun savePosts(posts: List<HomePosts>) {
        Coroutine.io {
            //val df: DateFormat = SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss")
            val currentDateTime: String = SimpleDateFormat("dd.MM.yyyy HH:mm").format(Calendar.getInstance().time)

            prefs.saveLastSavedAt(currentDateTime)
            db.getHomePOstDao().saveAllHomePosts(posts)
        }
    }



}