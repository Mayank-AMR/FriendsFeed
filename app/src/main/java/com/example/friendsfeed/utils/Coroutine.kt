package com.example.friendsfeed.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * @Project FriendsFeed
 * @Created_by Mayank Kumar on 14-09-2020 08:31 AM
 */
object Coroutine {

    fun main(work:suspend (()->Unit)) =
            CoroutineScope(Dispatchers.Main).launch {
                work()
            }

    fun io(work:suspend (()->Unit)) =
            CoroutineScope(Dispatchers.IO).launch {
                work()
            }

}