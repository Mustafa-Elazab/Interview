package com.roqay.mostafa.domain.remote

import com.roqay.mostafa.data.remote.dto.LanguageDTO
import com.roqay.mostafa.data.remote.dto.LanguagesDTO
import com.roqay.mostafa.data.remote.dto.QuestionDTO
import com.roqay.mostafa.data.remote.dto.QuestionsDTO

interface RemoteData {
    suspend fun getAllQuestionsByLanguageId(id:Int): QuestionsDTO
    suspend fun getAllLanguage(): LanguagesDTO
}