package com.example.friendsfeed.utils

import kotlinx.coroutines.*

/**
 * @Project FriendsFeed
 * @Created_by Mayank Kumar on 21-09-2020 01:33 PM
 */

fun <T> lazyDeferred(block: suspend CoroutineScope.() -> T): Lazy<Deferred<T>> {
    return lazy {
        GlobalScope.async(start = CoroutineStart.LAZY) {
            block.invoke(this)
        }
    }
}