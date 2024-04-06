package com.roqay.mostafa.domain.repository

import com.roqay.mostafa.data.remote.dto.LanguageDTO
import com.roqay.mostafa.data.remote.dto.LanguagesDTO
import com.roqay.mostafa.data.remote.dto.QuestionDTO
import com.roqay.mostafa.data.remote.dto.QuestionsDTO
import com.roqay.mostafa.data.remote.dto.Resource

interface HomeRepository {
    suspend fun getAllQuestionsByLanguageId(id:Int):Resource<QuestionsDTO>
    suspend fun getAllLanguage(): Resource<LanguagesDTO>
}