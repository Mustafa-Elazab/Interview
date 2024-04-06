package com.roqay.mostafa.data.remote

import com.roqay.mostafa.data.remote.dto.LanguageDTO
import com.roqay.mostafa.data.remote.dto.LanguagesDTO
import com.roqay.mostafa.data.remote.dto.QuestionDTO
import com.roqay.mostafa.data.remote.dto.QuestionsDTO
import com.roqay.mostafa.domain.remote.RemoteData
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class RemoteDataImpl(
    private val endPoint: String,
    private val httpClient: HttpClient,
) : RemoteData {
    override suspend fun getAllQuestionsByLanguageId(id: Int): QuestionsDTO {
      return  httpClient.get("$endPoint/GetAllQuestionsByLanguageId"){
          parameter("id" , id)
      }.body()
    }

    override suspend fun getAllLanguage(): LanguagesDTO {
        return  httpClient.get("$endPoint/GetAllLanguage").body()
    }
}