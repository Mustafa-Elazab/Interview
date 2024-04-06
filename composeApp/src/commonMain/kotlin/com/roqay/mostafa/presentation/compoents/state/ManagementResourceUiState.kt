package com.roqay.mostafa.presentation.compoents.state


import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.roqay.mostafa.data.remote.dto.Resource

@Composable
fun <T> ManagementResourceUiState(
    modifier: Modifier = Modifier,
    resourceUiState: Resource<T>,
    successView: @Composable (data: T) -> Unit,
    loadingView: @Composable () -> Unit = { Loading() },
    onTryAgain: () -> Unit,
    msgTryAgain: String = "No data to show",
    onCheckAgain: () -> Unit,
    msgCheckAgain: String = "An error has occurred"
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        when (resourceUiState) {
          //  is Resource.Empty -> Empty(modifier = modifier, onCheckAgain = onCheckAgain, msg = msgCheckAgain)
            is Resource.Failure -> Error(modifier = modifier, onTryAgain = onTryAgain, msg = msgTryAgain)
            Resource.Loading -> loadingView()
            is Resource.Success -> successView(resourceUiState.result)
            //Resource.Idle -> Unit
        }
    }
}