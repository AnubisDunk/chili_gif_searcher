package com.anubisdunk.chiligifsearcher.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.anubisdunk.chiligifsearcher.R
import com.anubisdunk.chiligifsearcher.ui.screens.MainScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChiliGifApp() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            GifTopAppBar()
        }

    ) {

            MainScreen(modifier = Modifier.padding(it))

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GifTopAppBar(modifier: Modifier = Modifier) {

    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ){
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
fun prew()
{
    ChiliGifApp()
}
