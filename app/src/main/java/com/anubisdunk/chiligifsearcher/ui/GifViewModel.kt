package com.anubisdunk.chiligifsearcher.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class GifViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(GifUiState())
    val uiState: StateFlow<GifUiState> = _uiState.asStateFlow()

    var userInput by mutableStateOf("")
        private set

    fun updateInput(userNewInput: String){
        userInput = userNewInput
    }
    fun exequteReq(){
        Log.d("ViewModel","Exec")
        updateInput("")
    }
}