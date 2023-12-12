package com.anubisdunk.chiligifsearcher.data

import com.anubisdunk.chiligifsearcher.model.Gif
import com.anubisdunk.chiligifsearcher.network.GifApiService

interface GifRepository {
//    suspend fun getGifs()
    suspend fun getGifs(search: String) : List<Gif>
}

class NetworkAgentsRepository(private val gifService : GifApiService) : GifRepository {
    override suspend fun getGifs(
        search: String
    ) : List<Gif> = gifService.gifSearch(search = search).data.map{
        items ->
        Gif(
            title = items.title,
            url = items.type
        )
    }
}