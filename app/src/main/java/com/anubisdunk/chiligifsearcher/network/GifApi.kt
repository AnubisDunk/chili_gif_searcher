package com.anubisdunk.chiligifsearcher.network

import com.anubisdunk.chiligifsearcher.model.GifDataList
import retrofit2.http.GET
import retrofit2.http.Query

interface GifApi {
    @GET("search")
    suspend fun getGifList(
        @Query("api_key") apiKey : String = "c5oCxWTg55BauK2A6iyzIlUSeLr5G8hy",
        @Query("limit") limit : Int = 20,
        @Query("q") search : String = "",

        @Query("rating") rating : String = "r",
        @Query("offset") offset : Int

    ) : GifDataList


}