package com.gamest.fastvideoplayer.glide

import android.os.Build
import android.util.Size
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@RequiresApi(Build.VERSION_CODES.Q)
@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView,imageUrl:String?)
{
    imageUrl?.let {
        val uri = imageUrl.toUri()
        val thumbnail = imageView.context.contentResolver.loadThumbnail(uri, Size(480,320),null)
        Glide.with(imageView.context)
            .load(thumbnail)
            .into(imageView)

    }
}