package com.roqay.mostafa.di


import com.roqay.mostafa.data.remote.RemoteDataImpl
import com.roqay.mostafa.data.repository.HomeRepositoryImpl
import com.roqay.mostafa.domain.remote.RemoteData
import com.roqay.mostafa.domain.repository.HomeRepository
import com.roqay.mostafa.domain.usecases.GetAllLanguagesUseCase
import com.roqay.mostafa.domain.usecases.GetAllQuestionsUseCase
import com.roqay.mostafa.presentation.screens.langaue.LanguageViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            viewModelModule,
           useCasesModule,
            repositoryModule,
            ktorModule,
          //  sqlDelightModule,
         //   mapperModule,
            dispatcherModule,
            platformModule()
        )
    }

val viewModelModule = module {
    factory { LanguageViewModel(get()) }
//    factory { CharactersFavoritesViewModel(get()) }
//    factory { params -> CharacterDetailViewModel(get(), get(), get(), params.get()) }
}
//
val useCasesModule: Module = module {
    factory { GetAllLanguagesUseCase(get(), get()) }
    factory { GetAllQuestionsUseCase(get(), get()) }
}
//
val repositoryModule = module {
    single<HomeRepository> { HomeRepositoryImpl(get()) }
  //  single<ICacheData> { CacheDataImp(get()) }
    single<RemoteData> { RemoteDataImpl(get(), get()) }


}

val ktorModule = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        prettyPrint = true
                        isLenient = true
                    }
                )
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
        }
    }

    single { "https://freeapi.gerasim.in/api/Interview" }
}

//val sqlDelightModule = module {
//    single { SharedDatabase(get()) }
//}

val dispatcherModule = module {
    factory { Dispatchers.Default }
}

//val mapperModule = module {
//    factory { ApiCharacterMapper() }
//}

fun initKoin() = initKoin {}



