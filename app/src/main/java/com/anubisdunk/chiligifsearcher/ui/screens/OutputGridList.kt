package com.anubisdunk.chiligifsearcher.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.anubisdunk.chiligifsearcher.R
import com.anubisdunk.chiligifsearcher.model.Gif

@Composable
fun OutputGridList(modifier: Modifier = Modifier, gifs: List<Gif>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2)
    ) {
        itemsIndexed(gifs){_,gif->
            GifCard(gif = gif)
        }
    }
}

@Composable
fun GifCard(
    gif: Gif,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = modifier
            .padding(dimensionResource(R.dimen.padding_small))
            .size(width = 240.dp, height = 100.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Text(
            text = gif.title +" |AA| " + gif.url,
            modifier = Modifier
                .padding(16.dp),
            textAlign = TextAlign.Center,
        )
    }
}