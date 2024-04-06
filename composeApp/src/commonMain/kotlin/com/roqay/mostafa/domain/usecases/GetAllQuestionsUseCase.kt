package com.roqay.mostafa.domain.usecases

import com.roqay.mostafa.data.remote.dto.LanguagesDTO
import com.roqay.mostafa.data.remote.dto.QuestionDTO
import com.roqay.mostafa.data.remote.dto.QuestionsDTO
import com.roqay.mostafa.data.remote.dto.Resource
import com.roqay.mostafa.domain.repository.HomeRepository
import com.roqay.mostafa.domain.usecases.type.BaseUseCase
import kotlinx.coroutines.CoroutineDispatcher


class GetAllQuestionsUseCase(
    private val repository: HomeRepository,
    dispatcher: CoroutineDispatcher,
) :
    BaseUseCase<Int, Resource<QuestionsDTO>>(dispatcher) {
    override suspend fun block(param: Int): Resource<QuestionsDTO> =
        repository.getAllQuestionsByLanguageId(param)
}