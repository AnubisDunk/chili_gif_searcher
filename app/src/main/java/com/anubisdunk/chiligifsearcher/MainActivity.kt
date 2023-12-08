package com.anubisdunk.chiligifsearcher

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anubisdunk.chiligifsearcher.data.Datasource
import com.anubisdunk.chiligifsearcher.model.GifItem
import com.anubisdunk.chiligifsearcher.ui.theme.ChiliGifSearcherTheme
import com.bumptech.glide.Glide
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChiliGifSearcherTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ChiliApp("Android")
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChiliApp(name: String, modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("Hello") }
//    OutlinedTextField(
//        value = text,
//        singleLine = true,
//        onValueChange = { text = it },
//        label = { Text("Gif") }
//    )
    GifList(
        gifList = Datasource().loadGifs()
    );
}

@Composable
fun GifList(gifList: List<GifItem>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(gifList) { item ->
            GifCard(
                gif = item,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun GifCard(gif: GifItem, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Column {

            GlideImage(
                model = gif.imageResourceId,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(),
//                    .height(194.dp)
                contentScale = ContentScale.Crop
            )
//            Text(
//                text = LocalContext.current.getString(gif.stringResourceId),
//                modifier = Modifier.padding(16.dp),
//                style = MaterialTheme.typography.headlineSmall
//            )
        }
    }
}

@Preview
@Composable
private fun GifCardPreview() {
    GifCard(GifItem(R.drawable.gif1))
}