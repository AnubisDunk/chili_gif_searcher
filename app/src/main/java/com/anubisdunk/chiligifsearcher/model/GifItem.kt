package com.anubisdunk.chiligifsearcher.model

data class GifDataList(
    val data : List<GifModel>,
    val pagination: Pagination

)
data class GifModel(
    val title : String,
    val type: String,
    val count : Int,
    val images : ImageModel
)
data class Gif(
    val title : String,
    val url: String
)
data class ImageModel(
    val downsized : DownsizedModel
)
data class DownsizedModel(
    val url : String
)
data class Pagination(
    val total_count: Int
)