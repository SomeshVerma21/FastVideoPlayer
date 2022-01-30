package com.vermaji.fastvideoplayer

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.vermaji.fastvideoplayer.fragments.videoListFragment.VideoListFragment

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
            .add(R.id.idMainActivityContainer,VideoListFragment())
            .commitNow()
    }
}