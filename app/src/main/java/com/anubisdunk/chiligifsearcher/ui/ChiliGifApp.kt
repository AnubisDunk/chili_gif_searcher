package com.anubisdunk.chiligifsearcher.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.anubisdunk.chiligifsearcher.R
import com.anubisdunk.chiligifsearcher.ui.screens.MainScreen


@Composable
fun ChiliGifApp() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            GifTopAppBar()
        }

    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),

            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            OutlinedTextField(
                value = "gifViewModel.userInput", //gifViewModel.userInput
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_medium)),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    disabledContainerColor = MaterialTheme.colorScheme.surface,
                ),
                onValueChange = {  }, //gifViewModel.updateInput(it)
                //isError = isGuessWrong,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { } //gifViewModel.exequteReq()
                )
            )
            MainScreen(
                modifier = Modifier
                    .padding(it)
                    .align(Alignment.CenterHorizontally)
            )
        }


    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GifTopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    modifier = Modifier
                        .size(dimensionResource(R.dimen.icon_size))
                        .padding(dimensionResource(R.dimen.padding_small)),
                    contentDescription = null,
                    painter = painterResource(R.drawable.gif_24px)

                )
                Text(
                    stringResource(R.string.app_name),
                    style = MaterialTheme.typography.displayLarge
                )
            }
        },
        modifier = modifier
    )
}

@Preview
@Composable
fun ChiliGifAppPrew(){
    ChiliGifApp()
}

