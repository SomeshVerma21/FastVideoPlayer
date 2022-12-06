package com.gamest.fastvideoplayer.domain.home.repo

import com.gamest.fastvideoplayer.data.model.Video


interface VideoMediaRepo {
    fun getMediaList() : List<Video>
}