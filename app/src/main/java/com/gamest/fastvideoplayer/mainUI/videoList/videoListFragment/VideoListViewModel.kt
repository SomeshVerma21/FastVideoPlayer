package com.gamest.fastvideoplayer.mainUI.videoList.videoListFragment

import android.content.ContentResolver
import android.content.ContentUris
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gamest.fastvideoplayer.mainUI.models.VideoProperty
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class VideoListViewModel(private val contentResolver: ContentResolver?) : ViewModel(){
    private var allMedia:MutableList<VideoProperty>? = null
    private val _videoList = MutableLiveData<MutableList<VideoProperty>>()
    val videoList:LiveData<MutableList<VideoProperty>>
        get() = _videoList

    fun filter(filter:String){
        when(filter){
            "wp"->{
                val list = allMedia?.filter { it.title.startsWith("VID-") }
                _videoList.value = (list as MutableList<VideoProperty>?)!!
            }
            "movies"->{
                val list = allMedia?.filter { it.duration.toLong()>4200 }
                _videoList.value = (list as MutableList<VideoProperty>?)!!
            }
            "videos"->{
                val list = allMedia?.filter { it.duration.toLong() in 91..179 }
                _videoList.value = (list as MutableList<VideoProperty>?)!!
            }
            "sorts"->{
                val list = allMedia?.filter { it.duration.toLong()<40&&!it.title.startsWith("VID-") }
                _videoList.value = (list as MutableList<VideoProperty>?)!!
            }
            "all"->{
                _videoList.value = allMedia!!
            }
        }
    }
    fun getData() {
        loadMedia()
    }
    private fun loadMedia()
    {
        val list = mutableListOf<VideoProperty>()
        val collection = if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.Q)
        {
            MediaStore.Video.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)
        }else{
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI
        }
        val projection = arrayOf(
            MediaStore.Video.Media._ID,
            MediaStore.Video.Media.DISPLAY_NAME,
            MediaStore.Video.Media.DURATION,
            MediaStore.Video.Media.SIZE
        )
        val selection = "${MediaStore.Video.Media.DURATION} >= ?"
        val selectionArg = arrayOf(
            TimeUnit.MILLISECONDS.convert(2, TimeUnit.SECONDS).toString()
        )
        val sortOrder = "${MediaStore.Video.Media.DISPLAY_NAME} ASC"
        viewModelScope.launch {
            val query = contentResolver?.query(
                collection,
                projection,
                selection,
                selectionArg,
                sortOrder
            )
            query?.use { cursor ->
                val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID)
                val nameColumn =
                    cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME)
                val durationColumn =
                    cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION)
                val sizeColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE)
                Log.i("view Model","cursor called size = ${cursor.count}")
                while (cursor.moveToNext())
                {
                    val id = cursor.getLong(idColumn)
                    val name = cursor.getString(nameColumn)
                    val duration = cursor.getInt(durationColumn)/1000 // changing value into seconds
                    val size = cursor.getInt(sizeColumn)

                    val contentUri:Uri = ContentUris.withAppendedId(
                        MediaStore.Video.Media.EXTERNAL_CONTENT_URI,id
                    )
                    Log.i("viewModel","$name")
                    list+=VideoProperty(name,contentUri.toString(),duration.toString(),size.toString()
                    )
                }
                _videoList.value = list
                allMedia = list
            }
        }
    }
}