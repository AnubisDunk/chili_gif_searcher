package com.anubisdunk.chiligifsearcher.ui.screens

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anubisdunk.chiligifsearcher.model.Gif
import com.anubisdunk.chiligifsearcher.repository.GifRepository
import com.anubisdunk.chiligifsearcher.utils.Constants.PAGE_SIZE
import com.anubisdunk.chiligifsearcher.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.launch
import retrofit2.http.Query


class MainScreenViewModel(
    val placeholder: String,
    val repository: GifRepository
) : ViewModel() {

    private val _searchUi = MutableStateFlow(placeholder)

    @OptIn(ExperimentalCoroutinesApi::class)
    val inputText = _searchUi.debounce(300)
        .distinctUntilChanged()
        .flatMapLatest {
            searchGifDeb(it)
        }

    val searchUi: StateFlow<String> = _searchUi


    private var curPage = 0

    var gifList = mutableStateOf<List<Gif>>(listOf())
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)
    var endReached = mutableStateOf(false)

    fun loadGifPaginated() {
        Log.e("Pag", "$curPage")
        viewModelScope.launch {
            isLoading.value = true
            val result = repository.getGifList(_searchUi.value, PAGE_SIZE, curPage * PAGE_SIZE)
            when (result) {
                is Resource.Success -> {
                    endReached.value = curPage * PAGE_SIZE >= result.data?.pagination!!.total_count
                    val gifEntries = result.data.data.mapIndexed { index, entry ->
                        Gif(title = entry.title, url = entry.images.downsized.url)
                    }
                    curPage++
                    loadError.value = ""
                    isLoading.value = false
                    gifList.value += gifEntries
                }

                is Resource.Error -> {
                    loadError.value = result.message!!
                    isLoading.value = false
                }
            }


        }
    }

    fun changeValue(value: String) {
        _searchUi.value = value
    }

    fun searchGifDeb(query: String): Flow<String> {
        if (query.isEmpty()) {
            return flowOf("Empty Result")
        } else {
            loadGifPaginated()
            return _searchUi
        }
    }
}

