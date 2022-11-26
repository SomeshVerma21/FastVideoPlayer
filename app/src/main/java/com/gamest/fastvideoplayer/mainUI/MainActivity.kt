package com.gamest.fastvideoplayer.mainUI

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gamest.fastvideoplayer.R
import com.gamest.fastvideoplayer.mainUI.videoList.videoListFragment.VideoListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        setupUI()
    }

    private fun setupUI()
    {
        supportFragmentManager.beginTransaction()
            .replace(R.id.idMainActivityContainer,VideoListFragment())
            .commitNow()
    }
}