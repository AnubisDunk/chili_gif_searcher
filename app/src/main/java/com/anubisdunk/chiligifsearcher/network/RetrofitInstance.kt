package com.anubisdunk.chiligifsearcher.network

import com.anubisdunk.chiligifsearcher.utils.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitInstance {
    fun provideGifApi() : GifApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(GifApi::class.java)
    }
}