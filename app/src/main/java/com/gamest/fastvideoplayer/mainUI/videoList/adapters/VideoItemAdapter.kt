package com.gamest.fastvideoplayer.mainUI.videoList.adapters

import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gamest.fastvideoplayer.R
import com.gamest.fastvideoplayer.databinding.VideoItemsBinding
import com.gamest.fastvideoplayer.mainUI.models.VideoProperty

class VideoItemAdapter(private val list: MutableList<VideoProperty>,private val clickListener: MediaClickListener)
    : RecyclerView.Adapter<VideoItemAdapter.VideoItemViewHolder>() {
    var videoItems = list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoItemViewHolder
    {
        return VideoItemViewHolder.getViewHolder(parent)
    }

    override fun onBindViewHolder(holder: VideoItemViewHolder, position: Int)
    {
        holder.bind(videoItems[position],clickListener)
    }

    override fun getItemCount(): Int {
        return list.size
    }
    class VideoItemViewHolder private constructor(private val binding: com.gamest.fastvideoplayer.databinding.VideoItemsBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: VideoProperty,clickListener: MediaClickListener) {
            binding.videoData = item
            binding.idMediaDuration.text = formatDuration(item.duration.toLong())
            val imageView = binding.imageView
            Glide.with(imageView.context).load(item.uri).placeholder(R.drawable.app_icon_312px).into(imageView)
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun getViewHolder(parent: ViewGroup): VideoItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = VideoItemsBinding.inflate(layoutInflater, parent, false)
                return VideoItemViewHolder(view)
            }
        }
    }
}
class MediaClickListener(val clickListener:(item:VideoProperty)->Unit)
{
    fun onClick(media:VideoProperty)
    {
        return clickListener(media)
    }
}

private fun formatDuration(seconds: Long): String = DateUtils.formatElapsedTime(seconds)