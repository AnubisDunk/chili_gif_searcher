package com.anubisdunk.chiligifsearcher.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

//@Serializable
//data class GifItem(
//    val id: String,
//    @SerialName(value = "url")
//    val url: String
//)

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