package com.anubisdunk.chiligifsearcher.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GifItem(
    val id: String,
    @SerialName(value = "url")
    val url: String
)