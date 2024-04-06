package com.roqay.mostafa.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class LanguagesDTO(
    val `data`: List<LanguageDTO>,
    val message: String,
    val result: Boolean
)

@Serializable
data class LanguageDTO(
    val language: String?=null,
    val languageId: Int?=null,
    val logo: String?=null,
    val youtubePlayListUrl: String?=null
)
