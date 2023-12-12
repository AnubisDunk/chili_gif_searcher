package com.anubisdunk.chiligifsearcher.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.anubisdunk.chiligifsearcher.ui.GifUiState

@Composable
fun HomeScreen(
    gifUiState: GifUiState,
    retryAction: () -> Unit,
    modifier: Modifier,
) {
    when (gifUiState) {
        is GifUiState.Loading -> LoadingScreen(modifier)
        is GifUiState.Success ->
            OutputGridList(
                gifs = gifUiState.gifSearch,
                modifier = modifier
            )

        is GifUiState.Error -> ErrorScreen(retryAction = retryAction, modifier)
    }
}