package com.roqay.mostafa.domain.usecases

import com.roqay.mostafa.data.remote.dto.LanguageDTO
import com.roqay.mostafa.data.remote.dto.LanguagesDTO
import com.roqay.mostafa.data.remote.dto.Resource
import com.roqay.mostafa.domain.repository.HomeRepository
import com.roqay.mostafa.domain.usecases.type.BaseUseCase
import kotlinx.coroutines.CoroutineDispatcher

class GetAllLanguagesUseCase(private val repository: HomeRepository, dispatcher: CoroutineDispatcher,) :
    BaseUseCase<Unit, Resource<LanguagesDTO>>(dispatcher) {
    override suspend fun block(param: Unit): Resource<LanguagesDTO> = repository.getAllLanguage()
}