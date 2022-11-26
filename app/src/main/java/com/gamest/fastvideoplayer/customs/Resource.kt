package com.gamest.fastvideoplayer.customs

import com.gamest.fastvideoplayer.data.model.Video

sealed class Resource<T>(val data: List<Video>? = null, val message: String? = null) {
    class Success<T>(data: List<Video>, s: String): Resource<T>(data)
    class Error<T>(message: String?, data: List<Video>? = null) : Resource<T>(data, message)
    class Loading<T>(data: List<Video>? = null) : Resource<T>(data)
}
