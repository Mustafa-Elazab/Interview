package com.roqay.mostafa.presentation.screens.questions

import cafe.adriel.voyager.core.model.screenModelScope
import com.roqay.mostafa.data.remote.dto.Resource
import com.roqay.mostafa.domain.usecases.GetAllQuestionsUseCase
import com.roqay.mostafa.presentation.mvi.BaseViewModel
import kotlinx.coroutines.launch

class QuestionViewModel(
    private val getAllQuestionsUseCase: GetAllQuestionsUseCase,
    private val languageId:Int
) : BaseViewModel<QuestionContract.Event,QuestionContract.State,QuestionContract.Effect>() {

    override fun createInitialState(): QuestionContract.State = QuestionContract.State(questions = Resource.Loading)

    override fun handleEvent(event: QuestionContract.Event) {
    }

    init {
        getQuestions(languageId)
    }

    private fun getQuestions(languageId: Int) {
        setState { copy(questions = Resource.Loading) }
        screenModelScope.launch {
            when (val response = getAllQuestionsUseCase.block(languageId)) {
                is Resource.Failure -> {
                    setState { copy(questions = Resource.Failure(response.exception)) }
                }
                Resource.Loading -> {
                    setState { copy(questions = Resource.Loading) }
                }
                is Resource.Success -> {
                    setState { copy(questions = Resource.Success(response.result)) }
                }
            }
        }
    }

}
