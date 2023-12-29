package com.anubisdunk.chiligifsearcher.network

import com.anubisdunk.chiligifsearcher.model.GifDataModel
import retrofit2.http.GET
import retrofit2.http.Query
import kotlin.random.Random

interface GifApiService {
    @GET("search")
    suspend fun gifSearch(
        @Query("api_key") apiKey : String = "c5oCxWTg55BauK2A6iyzIlUSeLr5G8hy",
       // @Query("limit") limit : Int = 20,
        @Query("q") search : String = "",

        @Query("rating") rating : String = "r",
       // @Query("offset") offset : Int = Random.nextInt(0,4999)

    ) : GifDataModel


}