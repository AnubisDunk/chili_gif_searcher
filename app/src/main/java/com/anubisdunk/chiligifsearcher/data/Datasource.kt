package com.anubisdunk.chiligifsearcher.data

import com.anubisdunk.chiligifsearcher.R
import com.anubisdunk.chiligifsearcher.model.GifItem

class Datasource() {
    fun loadGifs(): List<GifItem> {
        return listOf<GifItem>(
            GifItem( R.drawable.gif1),
            GifItem( R.drawable.gif2),
            GifItem( R.drawable.gif3),
            GifItem( R.drawable.gif4)
        )
    }
}