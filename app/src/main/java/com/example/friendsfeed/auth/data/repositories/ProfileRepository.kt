package com.example.friendsfeed.auth.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.friendsfeed.auth.data.network.MyApi
import com.example.friendsfeed.auth.data.network.SafeApiRequest
import com.example.friendsfeed.auth.data.preferences.PreferenceProvider
import com.example.friendsfeed.profile.response.ProfileResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

/**
 * @Project FriendsFeed
 * @Created_by Mayank Kumar on 27-09-2020 05:05 PM
 */
@Suppress("CAST_NEVER_SUCCEEDS")
class ProfileRepository(

        private val api: MyApi,
        private val prefs: PreferenceProvider

) : SafeApiRequest() {

    private var profile = MutableLiveData<ProfileResponse>()

    private val acceptType = "application/json"
    private val tk = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiY2RlNjJiMzhjYWQwMTI0ODdhMjcxOTI3MmNmM2M3MTU3YjgxMWUwZmZkNDIyNjE0YWRlMzk1NmY1NGJiNWRkYTAyMTQ2ZjM1Y2M4YTg1NTYiLCJpYXQiOjE2MDExOTU0NDQsIm5iZiI6MTYwMTE5NTQ0NCwiZXhwIjoxNjMyNzMxNDQ0LCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.fQKl153HubPMOeFqAfjAXl1c6mu1b9o1DlM8C8HjSpNRwejmShzQYvjXvp8dmdbwZhbmfJ4K38ImsqJCnHFOShiXFYVFMeH4PbpHkEGPzHwS7UQFTgrdZNXvwI2XM83MTipv3Nehfsr9cFO5V5qf3gK3xM7SMzJ-I8nTE-BvublNEdZQ7_739B4hosL02vbtkiSaYZ-Pmi0cK3hykU8XvqghclewtV3ZPYUBJ8GCZs1eE5Wtxj57A6OCuZYVfB-lG43zNHXdrOXzpD6KDNAmWE8OnmNi5ITJ7WJlwdiRzWF1DeBZmoP7nJOjdnLx7UiQCvZpIXnFJQohfJMHncn1CZRtSFDnIPGcrpRaRmJ4LdVwUlTdXHk8t3-56UDs1sp1DlBCD4pxG4wBIJGiqWpWySp26tT642hw2bdNKg2g9-yE0rwrpIaDTFTWKJYhQWMrue6BssthVC5sC697EFIYzEIg-i1SI-wFTWECyF__o1uQRK7TQ7iEhbeNrsIbl162N1flwDxyrRjH_eEpQswzf-1I4wCKC46FtDCjOiPAeX6_BMpeqskUTjCDVtc9w0KGXohyNv69A5hHcPtIgaZOVHSXAdxhRJi2TRBBx-Kx__Z_rk1Ali4PwRdjmJ4_SvWOV0PHEG2zrZ6mXjcQqyI-Qptlhx5QCYlIkMFAxQfI5MU"


    init {
        // observeForever observes if any change occurs in posts then savePosts() save it in local db.
        profile.observeForever {
        }
    }


    // getProfile() fetch profile data from backend API and put in 'profile' live data.
    suspend fun getProfile(): LiveData<ProfileResponse> {

        return withContext(Dispatchers.IO) {
            val response = apiRequest { api.getProfileData(acceptType, /*prefs.getUserAccessToken()*/tk) }
            profile.postValue(response)
            profile
        }
    }

}