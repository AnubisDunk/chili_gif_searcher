package com.anubisdunk.chiligifsearcher.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainScreenViewModel : ViewModel() {
    private val _searchUi = MutableStateFlow("")
    val searchUi : StateFlow<String>
        get() =  _searchUi

    fun ChangeValue(value : String){
        _searchUi.value = value

    }
}