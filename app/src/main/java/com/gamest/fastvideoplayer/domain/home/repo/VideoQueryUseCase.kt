package com.gamest.fastvideoplayer.domain.home.repo

import com.gamest.fastvideoplayer.customs.Resource
import com.gamest.fastvideoplayer.data.model.Video
import com.gamest.fastvideoplayer.domain.home.repo.VideoMediaRepo
import com.gamest.fastvideoplayer.presentation.home.navigation.FilterBy
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class VideoQueryUseCase @Inject constructor(
    private val videoMediaRepo: VideoMediaRepo
) {
    operator fun invoke() : Flow<Resource<List<Video>>> = flow {
        try {
            emit(Resource.Loading())
            val list = videoMediaRepo.getMediaList()
            delay(3000)
            emit(Resource.Success(list, "data found"))
        }catch (e:Exception){
            emit(Resource.Error("something went wrong" , null))
        }
    }
}