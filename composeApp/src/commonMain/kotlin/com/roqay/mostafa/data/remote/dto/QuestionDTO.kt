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
    val answer: String,
    val language: String,
    val languageId: Int,
    val languageTopicId: Int,
    val logo: String,
    val orderNo: Int,
    val question: String,
    val questionId: Int,
    val tags: String,
    val topicName: String
)
