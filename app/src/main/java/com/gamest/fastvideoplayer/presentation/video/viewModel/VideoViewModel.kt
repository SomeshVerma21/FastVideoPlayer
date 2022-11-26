package com.gamest.fastvideoplayer.presentation.video.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gamest.fastvideoplayer.customs.Resource
import com.gamest.fastvideoplayer.data.model.Video
import com.gamest.fastvideoplayer.presentation.VideoQueryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class VideoViewModel @Inject constructor(
    private val videoQueryUseCase: VideoQueryUseCase
) : ViewModel() {
    private val _state = mutableStateOf<List<Video>>(emptyList())
    val state:State<List<Video>> = _state
    init {
        getList()
    }

    private fun getList(){
        println("calling")
        videoQueryUseCase().onEach { result ->
            println("ssss$result")
            when(result){
                is Resource.Loading ->{
                    _state.value = emptyList()
                }
                is Resource.Success ->{
                    _state.value = result.data ?: emptyList()
                }
                is Resource.Error -> {
                    _state.value = emptyList()
                }
            }
        }.launchIn(viewModelScope)
    }
}