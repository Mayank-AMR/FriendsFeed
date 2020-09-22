package com.example.friendsfeed.postpackage.allpost

import androidx.lifecycle.ViewModel
import com.example.friendsfeed.auth.data.repositories.HomePostRepository
import com.example.friendsfeed.utils.lazyDeferred

class AllPostViewModel(
        repository: HomePostRepository

) : ViewModel() {
    private val acceptType = "application/json"

    val posts by lazyDeferred {
        repository.getPosts(acceptType, getAuthorization())
    }

    private fun getAuthorization(): String {
        // Return the Authorization to access the backend API.
        return "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiMzc1MDU3YjdmODcxZWM" +
                "0MDk3NWUxMzcwODg5ZWE4NTE4MzNiOTBjZDQzNDQ4MjY2MWU0YjExMWVmMDA4YmEzZDI2Nzg0MDY5YzhkO" +
                "DNmOWQiLCJpYXQiOjE2MDA2NzkwMTcsIm5iZiI6MTYwMDY3OTAxNywiZXhwIjoxNjMyMjE1MDE3LCJzdWI" +
                "iOiIxIiwic2NvcGVzIjpbXX0.AakL8xUXup46OlrO81Pi278FkWYrLCstMRe2vnCD71FQylq2Gy8QFL1ZZ" +
                "acgT_EpQLKhAQEa8FBcRAE9McUeJqZ7XBTBVEv_luIaXWkys61Gz-Bp_N27N1CgQssHQS1zJMIN0LSFs1fB" +
                "cLiBtLMY2JPnU8-8JLPZJI-TtM5IHJFOCfFc_iSCCGptYjO7FVM8_v8rc5UpPC1VNSzKgUTgehiSYRZRr9Yq" +
                "xbwIwbUgFVJ86hPsju-wmlkR1YtrEH9rBIDpCbi_oqWJt6JaPcYECup4-Wy4zR1Mt9sw2DfQusFMkQWq14TzO" +
                "X1OTk2yJrMkIFzZnKWPQJ8TmtMaKWhDjY0Ziau0b_B54H_izEQIIPOMsg1m9FqWzPQZyQ6z9QrA--4-qJqD" +
                "w5N5FXdbOSchQhkltZmTef_QLkcj8ajmafD_ESqm2JdPjEK_2QjOkR46NIoqiEZS1-6_-C3OhFGkkLlQD8" +
                "M2cjvjoGUf3T_yWYqawcIo_pwB5HtzGz40h91lo_WZPbXZ0VKdmfPx9yDq1CwU5Eudy8M60UgzFgL2Cr1U" +
                "qe_8ejHUpSesOy1eR9PKhQYMaEK3swLGfo6lu-XPjXq0PdNgyAWHLWAj4YZQv77iVZdWI-SqNoS0-QyswL" +
                "qxUI77TWuwbOmg0j-kOLE-FwV0W-2C4vzom5aucjKyUCY"
    }

}