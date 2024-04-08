package com.roqay.mostafa.presentation.screens.questions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.roqay.mostafa.data.remote.dto.QuestionDTO
import com.roqay.mostafa.presentation.compoents.composable.CustomAppBar
import com.roqay.mostafa.presentation.compoents.state.ManagementResourceUiState
import com.roqay.mostafa.presentation.screens.langaue.LanguageContract
import com.roqay.mostafa.presentation.screens.langaue.LanguageViewModel
import com.roqay.mostafa.presentation.screens.langaue.LanguagesList
import compose.icons.AllIcons
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.regular.PlusSquare
import org.koin.core.parameter.parametersOf

class QuestionScreen(
    private val languageId: Int,
) : Screen {

    override val key: ScreenKey = uniqueScreenKey

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val questionViewModel =
            getScreenModel<QuestionViewModel> { parametersOf(languageId) }
        val state by questionViewModel.uiState.collectAsState()
        val navigator = LocalNavigator.currentOrThrow


        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        Text("Questions")
                    }
                )
            },
        ) { padding ->
            ManagementResourceUiState(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize(),
                resourceUiState = state.questions,
                successView = { questions ->
                    QuestionList(
                        questions = questions.data,

                    )
                },
            )
        }
    }

     @Composable
    fun QuestionList(questions: List<QuestionDTO>) {
         LazyColumn(
             modifier = Modifier.fillMaxWidth(),
             contentPadding = PaddingValues(16.dp)
         ) {
             items (questions){ questions->
                 Column(
                     modifier = Modifier.fillMaxWidth()
                         .wrapContentHeight()
                         .padding(vertical = 25.dp),
                     verticalArrangement = Arrangement.Center,
                     horizontalAlignment = Alignment.CenterHorizontally
                 ) {
                     Row(
                         horizontalArrangement = Arrangement.SpaceBetween,
                         modifier = Modifier
                     ) {
                         Text(
                             questions.question!!,
                             style = MaterialTheme.typography.bodyMedium
                         )
                         IconButton(onClick = {}, content = { Icon( imageVector = FontAwesomeIcons.Regular.PlusSquare,contentDescription = "Action") })
                     }

                     Text(
                         questions.answer!!,
                         style = MaterialTheme.typography.bodyMedium
                     )
                 }
             }
         }
    }
}