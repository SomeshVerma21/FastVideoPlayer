package com.gamest.fastvideoplayer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gamest.fastvideoplayer.fragments.videoListFragment.VideoListFragment

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