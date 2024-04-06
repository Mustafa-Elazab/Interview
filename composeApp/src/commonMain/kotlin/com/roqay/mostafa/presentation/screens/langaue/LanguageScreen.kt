package com.roqay.mostafa.presentation.screens.langaue

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.flow.collectLatest
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.roqay.mostafa.data.remote.dto.LanguageDTO
import com.roqay.mostafa.presentation.compoents.composable.LoadImage
import com.roqay.mostafa.presentation.compoents.state.ManagementResourceUiState
import interview.composeapp.generated.resources.Res
import interview.composeapp.generated.resources.ic_cyclone
import io.github.aakira.napier.Napier
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.vectorResource

class LanguageScreen : Screen {
    override val key: ScreenKey = uniqueScreenKey

    @Composable
    override fun Content() {
        val languagesViewModel = getScreenModel<LanguageViewModel>()
        val state by languagesViewModel.uiState.collectAsState()
        val navigator = LocalNavigator.currentOrThrow

        LaunchedEffect(key1 = Unit) {
            languagesViewModel.effect.collectLatest { effect ->
//                when (effect) {
//                    is CharactersContract.Effect.NavigateToDetailCharacter ->
//                        navigator.push(CharacterDetailScreen(effect.idCharacter))
//
//                    is LanguageContract.Effect.NavigateToDetail -> navigator.push()
//                }
            }
        }

        Scaffold(
            //     topBar = { ActionAppBar { languagesViewModel.setEvent(LanguageContract.Event.OnLanguageClick) } }
        ) { padding ->
            ManagementResourceUiState(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize(),
                resourceUiState = state.languages,
                successView = { languages ->
                    LanguagesList(
                        languages = languages.data,
                        onLanguageClick = { id ->
                            languagesViewModel.setEvent(
                                LanguageContract.Event.OnLanguageClick(
                                    id
                                )
                            )
                        }
                    )
                },
                onTryAgain = {  },
                onCheckAgain = {  },
           )
        }
    }
}

@Composable
fun LanguagesList(
    languages: List<LanguageDTO>,
    onLanguageClick: (Int) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        items(languages) { language ->
            LanguageItem(
                language = language,
                onClick = { onLanguageClick(language.languageId!!) }
            )
        }
    }
}

@Composable
fun LanguageItem(
    language: LanguageDTO,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        KamelImage(
            asyncPainterResource(language.logo!!),
            language.language,
            contentScale = ContentScale.Fit,
            modifier = Modifier.fillMaxWidth().height(200.dp),
            onFailure = {
                KamelImage(
                    asyncPainterResource(language.logo!!),
                    language.language,
                    contentScale = ContentScale.Fit,
                    onLoading =  { progress -> CircularProgressIndicator(progress) },
                    modifier = Modifier.fillMaxWidth().height(200.dp),
                    onFailure = {
                        Image(
                            modifier = Modifier,
                            contentScale = ContentScale.Fit,
                            imageVector = vectorResource(Res.drawable.ic_cyclone),
                            contentDescription = null
                        )
                    }
                )
            }
        )
        Text(
            text = language.language!!,
            modifier = Modifier
                .fillMaxWidth(),
        )
    }
}