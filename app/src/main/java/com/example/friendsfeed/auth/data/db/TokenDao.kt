package com.example.friendsfeed.auth.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.friendsfeed.auth.data.db.entities.AccessToken
import com.example.friendsfeed.auth.data.db.entities.CURRENT_USER_ID
import com.example.friendsfeed.auth.data.network.responses.ResponseMessage

/**
 * @Project FriendsFeed
 * @Created_by Mayank Kumar on 15-09-2020 12:44 PM
 */
@Dao
interface TokenDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(accessTokenEntity: AccessToken): Long

    @Query("SELECT * FROM accesstoken WHERE uid = $CURRENT_USER_ID ")
    fun getAccessToken(): LiveData<AccessToken>
}