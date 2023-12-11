package com.anubisdunk.chiligifsearcher.network

import com.anubisdunk.chiligifsearcher.model.GifItem
import retrofit2.http.GET
import retrofit2.http.Query

interface GifApiService {
    @GET("search")
    suspend fun getGifs(
        @Query ("q") search: String,
        @Query ("api_key") apiKey: String = "c5oCxWTg55BauK2A6iyzIlUSeLr5G8hy",
    ): List<GifItem>
}