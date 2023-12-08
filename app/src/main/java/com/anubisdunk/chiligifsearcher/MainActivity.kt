package com.anubisdunk.chiligifsearcher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anubisdunk.chiligifsearcher.data.Datasource
import com.anubisdunk.chiligifsearcher.model.GifItem
import com.anubisdunk.chiligifsearcher.ui.theme.ChiliGifSearcherTheme

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

@Composable
fun ChiliApp(name: String, modifier: Modifier = Modifier) {
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

@Composable
fun GifCard(gif: GifItem, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Column {
            Image(
                painter = painterResource(gif.imageResourceId),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(),
//                    .height(194.dp),
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