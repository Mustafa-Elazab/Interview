package com.roqay.mostafa.presentation.screens.questions

import com.roqay.mostafa.data.remote.dto.LanguagesDTO
import com.roqay.mostafa.data.remote.dto.QuestionsDTO
import com.roqay.mostafa.data.remote.dto.Resource
import com.roqay.mostafa.presentation.mvi.UiEffect
import com.roqay.mostafa.presentation.mvi.UiEvent
import com.roqay.mostafa.presentation.mvi.UiState

interface QuestionContract {

    sealed interface Event : UiEvent {
    }

    data class State(
        val questions: Resource<QuestionsDTO>
    ) : UiState

    sealed interface Effect : UiEffect {
    }
}