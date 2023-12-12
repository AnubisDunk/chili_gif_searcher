package com.anubisdunk.chiligifsearcher.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.anubisdunk.chiligifsearcher.GifSearchApplication
import com.anubisdunk.chiligifsearcher.data.GifRepository
import com.anubisdunk.chiligifsearcher.model.Gif
import com.anubisdunk.chiligifsearcher.network.GifApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okio.IOException
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


sealed interface GifUiState {
    data class Success(val gifSearch: List<Gif>) : GifUiState
    object Error : GifUiState
    object Loading : GifUiState
}

class GifViewModel(
    private val gifRepository: GifRepository
) : ViewModel() {
    var gifUiState : GifUiState by mutableStateOf(GifUiState.Loading)
        private set

    var userInput by mutableStateOf("")
        private set

    init {
        getGifs("")
    }

    fun updateInput(userNewInput: String) {
        userInput = userNewInput

    }

    fun exequteReq() {
//        val interceptor = HttpLoggingInterceptor()
//        interceptor.level = HttpLoggingInterceptor.Level.BODY
//        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
//
//        val retrofit = Retrofit.Builder()
//            .addConverterFactory(GsonConverterFactory.create())
//            .baseUrl("https://api.giphy.com/v1/gifs/")
//            .client(client)
//            .build();
//        val testApi = retrofit.create(GifApiService::class.java)
//
//        CoroutineScope(Dispatchers.IO).launch {
//            val test = testApi.gifSearch(seatuserInput)
//            Log.e("E", test.toString())


        //Log.e("E", userInput)
        getGifs(userInput)
    }


    fun getGifs(lang: String) {
        viewModelScope.launch {
            gifUiState = GifUiState.Loading
            gifUiState = try {
                GifUiState.Success(gifRepository.getGifs(lang))
            } catch (e: IOException){
                Log.e("Error", e.toString())
                GifUiState.Error
            } catch (e: HttpException){
                Log.e("Error", e.toString())
                GifUiState.Error
            }
        }
    }
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as GifSearchApplication)
                val gifRepository = application.container.gifRepository
                GifViewModel(gifRepository = gifRepository)
            }
        }
    }
}
