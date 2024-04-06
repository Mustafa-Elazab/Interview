package com.roqay.mostafa.data.repository

import com.roqay.mostafa.data.remote.dto.LanguageDTO
import com.roqay.mostafa.data.remote.dto.LanguagesDTO
import com.roqay.mostafa.data.remote.dto.QuestionDTO
import com.roqay.mostafa.data.remote.dto.QuestionsDTO
import com.roqay.mostafa.data.remote.dto.Resource
import com.roqay.mostafa.domain.remote.RemoteData
import com.roqay.mostafa.domain.repository.HomeRepository

class HomeRepositoryImpl(
    private val remoteData: RemoteData,
) : HomeRepository {
    override suspend fun getAllQuestionsByLanguageId(id: Int): Resource<QuestionsDTO>{
       val isFailed = remoteData.getAllQuestionsByLanguageId(id).data.isEmpty()
        return if(!isFailed){
            try {
                Resource.Success(
                    remoteData.getAllQuestionsByLanguageId(id)
                )
            } catch (e: Exception) {
                e.printStackTrace()
                Resource.Failure(e)
            }
        }else{
            Resource.Failure(Exception(remoteData.getAllQuestionsByLanguageId(id).message))
        }
    }

    override suspend fun getAllLanguage(): Resource<LanguagesDTO> {
        val isFailed = remoteData.getAllLanguage().data.isEmpty()
        return if(!isFailed){
            try {
                Resource.Success(
                    remoteData.getAllLanguage()
                )
            } catch (e: Exception) {
                e.printStackTrace()
                Resource.Failure(e)
            }
        }else{
            Resource.Failure(Exception(remoteData.getAllLanguage().message))
        }
    }
}