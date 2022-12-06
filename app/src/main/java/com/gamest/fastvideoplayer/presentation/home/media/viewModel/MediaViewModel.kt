package com.gamest.fastvideoplayer.presentation.home.media.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gamest.fastvideoplayer.customs.Resource
import com.gamest.fastvideoplayer.data.model.Video
import com.gamest.fastvideoplayer.domain.home.repo.VideoQueryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MediaViewModel @Inject constructor(
    private val videoQueryUseCase: VideoQueryUseCase
) : ViewModel() {
    private val _isLoading = mutableStateOf(false)
    val isLoading:State<Boolean> = _isLoading
    private val _state = mutableStateOf<List<Video>>(emptyList())
    val state:State<List<Video>> = _state
    init {
        getList()
    }

    private fun getList(){
        videoQueryUseCase().onEach { result ->
            when(result){
                is Resource.Loading ->{
                    _isLoading.value = true
                    _state.value = emptyList()
                }
                is Resource.Success ->{
                    _isLoading.value = false
                    _state.value = result.data ?: emptyList<Video>().also { it ->
                        it.filter { it.title.startsWith("VID") }
                    }
                }
                is Resource.Error -> {
                    _isLoading.value = false
                    _state.value = emptyList()
                }
            }
        }.launchIn(viewModelScope)
    }

//    fun filterItems(filterBy: FilterBy){
//        when(filterBy){
//            FilterBy.All -> {
//                getList()
//            }
//            FilterBy.Movie -> {
//
//            }
//            FilterBy.Videos -> {
//
//            }
//            FilterBy.WebSeries -> {
//
//            }
//            FilterBy.WhatsApp -> {
//
//            }
//            FilterBy.Bing -> {
//
//            }
//        }
//    }
}