package com.roqay.mostafa.presentation.screens.langaue

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import com.roqay.mostafa.data.remote.dto.LanguageDTO
import com.roqay.mostafa.presentation.compoents.state.ManagementResourceUiState
import com.roqay.mostafa.presentation.screens.questions.QuestionScreen


class LanguageScreen : Screen {
    override val key: ScreenKey = uniqueScreenKey

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val languagesViewModel = getScreenModel<LanguageViewModel>()
        val state by languagesViewModel.uiState.collectAsState()
        val navigator = LocalNavigator.currentOrThrow

        LaunchedEffect(key1 = Unit) {
            languagesViewModel.effect.collectLatest { effect ->
                when (effect) {
                    is LanguageContract.Effect.NavigateToDetail ->
                        navigator.push(QuestionScreen(effect.id))

                }
            }
        }

        Scaffold(
            topBar = {
                TopAppBar(
                    colors = topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        Text("Language")
                    }
                )
            },

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
//                onTryAgain = {  },
//                onCheckAgain = {  },
           )
        }
    }
}

@Composable
fun LanguagesList(
    languages: List<LanguageDTO>,
    onLanguageClick: (Int) -> Unit,
) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(
            start = 4.dp,
            top = 4.dp,
            end = 4.dp,
            bottom = 4.dp
        ),
        content = {
            items(languages) { language ->
                LanguageItem(
                language = language,
              onClick = { onLanguageClick(language.languageId!!) }
           )
            }
        }
    )
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
        AsyncImage(
            language.logo,
            null,
            modifier = Modifier.height(120.dp).aspectRatio(1.0f).padding(4.dp),
            contentScale = ContentScale.FillBounds,
        )
        Text(
            text = language.language!!,
        )
    }
}