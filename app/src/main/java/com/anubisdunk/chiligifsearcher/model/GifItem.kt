package com.anubisdunk.chiligifsearcher.model

data class GifDataModel(
    val data : List<GifModel>,

)
data class GifModel(
    val title : String,
    val type: String,
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