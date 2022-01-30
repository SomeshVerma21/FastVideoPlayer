package com.vermaji.fastvideoplayer.fragments.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vermaji.fastvideoplayer.R
import com.vermaji.fastvideoplayer.databinding.VideoItemsBinding
import com.vermaji.fastvideoplayer.media.VideoProperty

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
    class VideoItemViewHolder private constructor(private val binding: VideoItemsBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: VideoProperty,clickListener: MediaClickListener) {
            binding.videoData = item
            val imageView = binding.imageView
            Glide.with(imageView.context).load(item.uri).placeholder(R.drawable.ic_player_icon).into(imageView)
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
class MediaClickListener(val clickListener:(itemUri:String)->Unit)
{
    fun onClick(media:String)
    {
        return clickListener(media)
    }
}