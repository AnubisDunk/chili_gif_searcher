package com.anubisdunk.chiligifsearcher.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.anubisdunk.chiligifsearcher.R
import com.anubisdunk.chiligifsearcher.model.Gif
import com.anubisdunk.chiligifsearcher.ui.theme.loadingInit
import com.anubisdunk.chiligifsearcher.ui.theme.loadingTarget

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OutputGridList(viewModel: MainScreenViewModel) {
    val gifList by remember { viewModel.gifList }
    val endReached by remember { viewModel.endReached }
    val isLoading by remember { viewModel.isLoading }
    var activePhotoId by remember { mutableIntStateOf(-1) }


    val haptics = LocalHapticFeedback.current

    if (activePhotoId != -1) {
        FullScreenImage(
            gifIndex = activePhotoId,
            entries = gifList,
            onDismiss = { activePhotoId = -1 }
        )
    }
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
                Modifier
                    .combinedClickable(
                        onClick = { },
                        onLongClick = {
                            //activePhotoId = it
                            haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                        },
                    )
                    .animateItemPlacement()
            )
        }
    }
}

@Composable
fun FullScreenImage(gifIndex: Int, entries: List<Gif>, onDismiss: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize(0.5f)
            .clickable { onDismiss() }

    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(entries[gifIndex].url)
                .decoderFactory(ImageDecoderDecoder.Factory())
                .build(),
            placeholder = rememberAsyncImagePainter(CustomPlaceholder()),
            contentScale = ContentScale.Crop,
            contentDescription = null,
        )
    }
}

@Composable
fun GifCard(
    gifIndex: Int,
    entries: List<Gif>,
    modifier: Modifier
) {
        AsyncImage(
            modifier = modifier,
            model = ImageRequest.Builder(LocalContext.current)
                .data(entries[gifIndex].url)
                .decoderFactory(ImageDecoderDecoder.Factory())
                .build(),
            error = painterResource(R.drawable.error_24px),
            placeholder = rememberAsyncImagePainter(CustomPlaceholder()),
            contentScale = ContentScale.Crop,
            contentDescription = entries[gifIndex].title,
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

//@Composable
//fun CustomPlaceholder(modifier: Modifier) {
//    Box(modifier) {
//        CircularProgressIndicator(
//            modifier = Modifier.width(64.dp),
//            color = MaterialTheme.colorScheme.secondary,
//            trackColor = MaterialTheme.colorScheme.surfaceVariant,
//        )
//    }
//}
@Composable
fun CustomPlaceholder() {
    val infiniteTransition = rememberInfiniteTransition()
    val colorAnim by infiniteTransition.animateColor(
        initialValue = loadingInit,
        targetValue = loadingTarget,
        animationSpec = infiniteRepeatable(
            animation = tween(500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = "LoadingColor"
    )
    Box() {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = colorAnim
            ),
            modifier = Modifier
                .size(width = 240.dp, height = 100.dp)
        ) {
//            CircularProgressIndicator(
//                color = MaterialTheme.colorScheme.secondary,
//                trackColor = MaterialTheme.colorScheme.surfaceVariant,
//                modifier = Modifier.align(CenterHorizontally)
//            )
        }
    }

}
