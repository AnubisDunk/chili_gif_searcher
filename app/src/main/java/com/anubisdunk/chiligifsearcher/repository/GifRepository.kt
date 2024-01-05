package com.anubisdunk.chiligifsearcher.repository

import android.util.Log
import com.anubisdunk.chiligifsearcher.model.GifDataList
import com.anubisdunk.chiligifsearcher.network.GifApi
import com.anubisdunk.chiligifsearcher.utils.Resource

class GifRepository(
    private val api: GifApi
) {
    suspend fun getGifList(search: String, limit: Int, offset: Int): Resource<GifDataList> {
        val response = try{
            Log.e("Req", "${search}")
            api.getGifList(search = search, limit = limit, offset = offset)
        } catch (e: Exception){

            return Resource.Error("Something went wrong")
        }
        return Resource.Success(response)
    }
}

