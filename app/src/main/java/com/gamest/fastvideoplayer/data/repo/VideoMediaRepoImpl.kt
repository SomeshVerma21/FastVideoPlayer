package com.gamest.fastvideoplayer.data.repo

import android.app.Application
import android.content.ContentUris
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import com.gamest.fastvideoplayer.data.model.Video
import com.gamest.fastvideoplayer.domain.repo.VideoMediaRepo
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class VideoMediaRepoImpl @Inject constructor(
    private val application: Application,
    private var mediaVideos: List<Video> = emptyList()
    ) : VideoMediaRepo{
    init {
        loadMedia()
    }
    private fun loadMedia()
    {
        val list = mutableListOf<Video>()
        val collection = if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.Q)
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

        val query = application.contentResolver?.query(
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

                val contentUri: Uri = ContentUris.withAppendedId(
                    MediaStore.Video.Media.EXTERNAL_CONTENT_URI,id
                )
                Log.i("viewModel","$name")
                list+= Video(name,contentUri.toString(),duration.toString(),size.toString())
            }
            mediaVideos = list
        }
    }


    override fun getMediaList(): List<Video> {
        return mediaVideos
    }
}