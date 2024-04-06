package com.roqay.mostafa.domain.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class Languages(
    val `data`: List<Data>,
    val message: String,
    val result: Boolean
) {
    @Serializable
    data class Data(
        val language: String,
        val languageId: Int,
        val logo: String,
        val youtubePlayListUrl: String
    )
}