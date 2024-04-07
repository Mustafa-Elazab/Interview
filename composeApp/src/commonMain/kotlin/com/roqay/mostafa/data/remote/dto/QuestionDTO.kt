package com.roqay.mostafa.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class QuestionsDTO(
    val `data`: List<QuestionDTO>,
    val message: String,
    val result: Boolean
)

@Serializable
data class QuestionDTO(
    val answer: String?=null,
    val language: String?=null,
    val languageId: Int?=null,
    val languageTopicId: Int?=null,
    val logo: String?=null,
    val orderNo: Int?=null,
    val question: String?=null,
    val questionId: Int?=null,
    val tags: String?=null,
    val topicName: String?=null
)
