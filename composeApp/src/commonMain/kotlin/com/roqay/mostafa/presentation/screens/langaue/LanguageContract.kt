package com.roqay.mostafa.presentation.screens.langaue

import com.roqay.mostafa.data.remote.dto.LanguageDTO
import com.roqay.mostafa.data.remote.dto.LanguagesDTO
import com.roqay.mostafa.data.remote.dto.Resource
import com.roqay.mostafa.presentation.mvi.UiEffect
import com.roqay.mostafa.presentation.mvi.UiEvent
import com.roqay.mostafa.presentation.mvi.UiState

interface LanguageContract {

    sealed interface Event : UiEvent {
        data class OnLanguageClick(val id: Int) : Event
    }

    data class State(
        val languages: Resource<LanguagesDTO>
    ) : UiState

    sealed interface Effect : UiEffect {
        data class NavigateToDetail(val id: Int) : Effect
    }
}