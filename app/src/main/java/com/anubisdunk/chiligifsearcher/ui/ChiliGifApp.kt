package com.anubisdunk.chiligifsearcher.ui

import android.util.Log
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.anubisdunk.chiligifsearcher.R
import com.anubisdunk.chiligifsearcher.network.RetrofitInstance
import com.anubisdunk.chiligifsearcher.repository.GifRepository
import com.anubisdunk.chiligifsearcher.ui.screens.MainScreenViewModel
import com.anubisdunk.chiligifsearcher.ui.screens.OutputGridList
import com.anubisdunk.chiligifsearcher.ui.screens.Retry


@Composable
fun ChiliGifApp() {
    val gifApi = RetrofitInstance.provideGifApi()

    val vm = viewModel<MainScreenViewModel>(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return MainScreenViewModel(
                    placeholder = "",
                    repository = GifRepository(gifApi)
                ) as T
            }
        }
    )

    val searchState by vm.searchUi.collectAsState()
    val isLoading by remember { vm.isLoading }
    val loadError by remember { vm.loadError }
    val dbText by vm.inputText.collectAsState("Empty")
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            GifTopAppBar()
        }

    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),

            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            OutlinedTextField(
                value = searchState,
                maxLines = 1,
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_medium)),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    disabledContainerColor = MaterialTheme.colorScheme.surface,
                ),
                onValueChange = {
                    vm.changeValue(it)
                },

                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { }
                )
            )
            if (searchState.isNotBlank() && loadError.isEmpty()) {
                vm.clearList()
                OutputGridList(
                    viewModel = vm
                )
            }
            if (loadError.isNotEmpty()) {
                Retry(error = loadError) {
                    vm.loadGifPaginated()
                }
            }
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
fun ChiliGifAppPrew() {
    ChiliGifApp()
}

