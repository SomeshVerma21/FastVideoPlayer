package com.vermaji.fastvideoplayer.media

import android.content.Context
import com.google.android.exoplayer2.ExoPlayer

class MExoPlayer{
    companion object{
        @Volatile
        private var INSTANCE:ExoPlayer?=null

        fun getInstance(context:Context): ExoPlayer? {
            var instance = INSTANCE
            if (instance==null){
                instance = ExoPlayer.Builder(context).build()
            }
            INSTANCE = instance
            return INSTANCE
        }
    }
}