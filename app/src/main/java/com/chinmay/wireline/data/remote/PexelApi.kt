package com.chinmay.wireline.data.remote

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface PexelApi {

    @Headers("Authorization: $API_KEY")
    @GET("curated")
    suspend fun getPexels(
        @Query("page") page: Int,
        @Query("per_page") pageCount: Int
    ): PexelResponse

    companion object {
        const val BASE_URL = "https://api.pexels.com/v1/"
        const val API_KEY = "zcFzsKnpGEI3HuxTDtDARByOXsNp47vsFdg0mT72AXCPKHqjyMkxjGrT"
    }
}

data class PexelResponse(
    val photos: List<PexelDto>,
    val next_page: String?
)
