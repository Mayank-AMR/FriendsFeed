package com.example.friendsfeed.auth.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.friendsfeed.auth.data.db.entities.HomePosts

/**
 * @Project FriendsFeed
 * @Created_by Mayank Kumar on 20-09-2020 03:14 PM
 */

@Dao
interface HomePostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllHomePosts(homepost: List<HomePosts>)

    @Query("SELECT * FROM homeposts")
    fun getHomePosts(): LiveData<List<HomePosts>>

}