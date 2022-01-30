package com.vermaji.fastvideoplayer.fragments.videoListFragment

import android.content.ContentResolver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory(private val contentResolver:ContentResolver?): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VideoListModel::class.java))
            return VideoListModel(contentResolver) as T
        else
            throw IllegalArgumentException("Unknown view model found")
    }

}