package com.anubisdunk.chiligifsearcher.data

import com.anubisdunk.chiligifsearcher.network.GifApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val gifRepository : GifRepository
}
class DefaultAppContainer : AppContainer {
    private val BASE_URL = "https://api.giphy.com/v1/gifs/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build();

    private val retrofitService: GifApiService by lazy {
        retrofit.create(GifApiService::class.java)
    }
    override val gifRepository: GifRepository by lazy {
        NetworkAgentsRepository(retrofitService)
    }
}