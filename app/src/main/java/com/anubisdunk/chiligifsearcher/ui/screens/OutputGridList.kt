package com.anubisdunk.chiligifsearcher.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.anubisdunk.chiligifsearcher.R
import com.anubisdunk.chiligifsearcher.model.Gif

@Composable
fun OutputGridList(modifier: Modifier = Modifier, gifs: List<Gif>) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(150.dp),
        verticalItemSpacing = 4.dp,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        itemsIndexed(gifs) { _, gif ->
            GifCard(gif = gif)
        }
    }
}

@Composable
fun GifCard(
    gif: Gif,
    modifier: Modifier = Modifier
) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(gif.url)
                .decoderFactory(ImageDecoderDecoder.Factory())
                .build(),
            error = painterResource(R.drawable.error_24px),
            placeholder = rememberAsyncImagePainter(CustomPlaceholder(modifier.padding(16.dp))),
//            placeholder = painterResource(R.drawable.progress_activity_24px),
            contentScale = ContentScale.Crop,
            contentDescription = null,
        )

}
@Composable
fun CustomPlaceholder(modifier: Modifier){
    Box(modifier) {
        CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    }
}