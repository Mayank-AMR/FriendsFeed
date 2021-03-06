package com.example.friendsfeed.auth.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.friendsfeed.auth.data.db.entities.AccessToken
import com.example.friendsfeed.auth.data.db.entities.HomePosts

/**
 * @Project FriendsFeed
 * @Created_by Mayank Kumar on 15-09-2020 12:28 PM
 */
@Database(
        entities = [AccessToken::class, HomePosts::class],
        version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getTokenDao(): TokenDao
    abstract fun getHomePOstDao(): HomePostDao

    companion object {

        @Volatile
        private var instance: AppDatabase? = null

        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "friendsfeed.db"
                ).build()
    }
}