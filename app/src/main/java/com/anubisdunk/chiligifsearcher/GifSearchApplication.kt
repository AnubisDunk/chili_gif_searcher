package com.anubisdunk.chiligifsearcher

import android.app.Application
import com.anubisdunk.chiligifsearcher.data.AppContainer
import com.anubisdunk.chiligifsearcher.data.DefaultAppContainer

class GifSearchApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }

}