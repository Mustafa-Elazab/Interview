package com.roqay.mostafa.presentation.screens.langaue

import com.roqay.mostafa.data.remote.dto.Resource
import com.roqay.mostafa.domain.usecases.GetAllLanguagesUseCase
import com.roqay.mostafa.presentation.mvi.BaseViewModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.launch

class LanguageViewModel(private val getAllLanguages: GetAllLanguagesUseCase) :
    BaseViewModel<LanguageContract.Event, LanguageContract.State, LanguageContract.Effect>() {


    init {
        getLanguages()
    }

    override fun createInitialState(): LanguageContract.State =
        LanguageContract.State(languages = Resource.Loading)


    override fun handleEvent(event: LanguageContract.Event) {
        when (event) {
            is LanguageContract.Event.OnLanguageClick -> {
                setEffect {
                    LanguageContract.Effect.NavigateToDetail(
                        event.id
                    )
                }
            }

            LanguageContract.Event.OnTryCheckAgainClick -> {
                getLanguages()
            }
        }
    }

    private fun getLanguages() {
        setState { copy(languages = Resource.Loading) }
        screenModelScope.launch {
            when (val response = getAllLanguages.block(Unit)) {
                is Resource.Failure -> {
                    setState { copy(languages = Resource.Failure(response.exception)) }
                    android.util.Log.d("STATE", "Content: ${response.exception}")
                }
                Resource.Loading -> {
                    setState { copy(languages = Resource.Loading) }
                }
                is Resource.Success -> {
                    setState { copy(languages = Resource.Success(response.result)) }
                    android.util.Log.d("STATE", "Content: ${response.result}")
                }
            }
        }
    }

}