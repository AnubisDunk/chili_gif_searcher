package com.anubisdunk.chiligifsearcher.ui.screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.anubisdunk.chiligifsearcher.R
import com.anubisdunk.chiligifsearcher.model.Gif

@Composable
fun OutputGridList(viewModel: MainScreenViewModel) {
    val gifList by remember { viewModel.gifList }
    val endReached by remember { viewModel.endReached }
    val isLoading by remember { viewModel.isLoading }
    var activePhotoId by remember { mutableStateOf(0) }
    LazyVerticalStaggeredGrid(

        columns = StaggeredGridCells.Adaptive(150.dp),
        verticalItemSpacing = 4.dp,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        val itemCount = if (gifList.size % 2 == 0) {
            gifList.size / 2
        } else {
            gifList.size / 2 + 1
        }
        items(itemCount) {
            if (it >= itemCount - 1 && !endReached && !isLoading) { //<--- !isLoadingTrouble
                viewModel.loadGifPaginated()
            }
            GifCard(
                gifIndex = it,
                entries = gifList,
                Modifier.clickable {
                    activePhotoId = it
                    Log.e("Tap", "$it")
                }
            )
        }
//        if (activePhotoId != null) {
//            FullScreenImage(
//                photo = photos.first { it.id == activePhotoId },
//                onDismiss = { activePhotoId = null }
//            )
//        }
    }

}

@Composable
fun GifCard(
    gifIndex: Int,
    entries: List<Gif>,
    modifier: Modifier
) {
    AsyncImage(

        model = ImageRequest.Builder(LocalContext.current)
            .data(entries[gifIndex * 2].url)
            .decoderFactory(ImageDecoderDecoder.Factory())
            .build(),
        error = painterResource(R.drawable.error_24px),
        placeholder = rememberAsyncImagePainter(CustomPlaceholder(Modifier.padding(16.dp))),
        contentScale = ContentScale.Crop,
        contentDescription = null,
    )

}

@Composable
fun Retry(
    error: String,
    onRetry: () -> Unit
) {
    Column {
        Text(
            text = error,
            color = MaterialTheme.colorScheme.error,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { onRetry() },
            modifier = Modifier.align(CenterHorizontally)

        ) {
            Text(text = "Retry")

        }
    }
}

@Composable
fun CustomPlaceholder(modifier: Modifier) {
    Box(modifier) {
        CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    }
}