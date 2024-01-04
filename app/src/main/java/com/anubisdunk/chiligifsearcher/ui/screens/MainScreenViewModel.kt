package com.anubisdunk.chiligifsearcher.ui.screens

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anubisdunk.chiligifsearcher.model.Gif
import com.anubisdunk.chiligifsearcher.repository.GifRepository
import com.anubisdunk.chiligifsearcher.utils.Constants.PAGE_SIZE
import com.anubisdunk.chiligifsearcher.utils.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch


class MainScreenViewModel(
    val placeholder: String,
    val repository: GifRepository
) : ViewModel() {

    private val _searchUi = MutableStateFlow(placeholder)

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
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
        Log.e("Pag", "Page nr = $curPage")
        viewModelScope.launch {
            isLoading.value = true
            when (val result = repository.getGifList(_searchUi.value, PAGE_SIZE, curPage * PAGE_SIZE)) {
                is Resource.Success -> {
                    endReached.value = curPage * PAGE_SIZE >= result.data?.pagination!!.total_count
                    val gifEntries = result.data.data.mapIndexed { _, entry ->
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

    fun clearList(){
       // Log.e("Er", "Clear")
        curPage = 0
        loadError.value = ""
        isLoading.value = false
        gifList.value = emptyList()
    }

    fun changeValue(value: String) {
        _searchUi.value = value
    }

    private fun searchGifDeb(query: String): Flow<String> {
        return if (query.isEmpty()) {
            flowOf("Empty Result")
        } else {
            loadGifPaginated()
            _searchUi
        }
    }
}

