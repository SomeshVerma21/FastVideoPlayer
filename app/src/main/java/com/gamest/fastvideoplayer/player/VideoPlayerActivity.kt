package com.gamest.fastvideoplayer.player

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.gamest.fastvideoplayer.R
import com.gamest.fastvideoplayer.databinding.ActivityVideoPlayerBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.PlayerView

class VideoPlayerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVideoPlayerBinding
    private var mediaUri:String?=null
    private var mediaName:String? =null
    private var player:ExoPlayer? = null
    private val resignedMode = arrayOf(AspectRatioFrameLayout.RESIZE_MODE_FIT,AspectRatioFrameLayout.RESIZE_MODE_ZOOM,
                                AspectRatioFrameLayout.RESIZE_MODE_FILL)
    private var currentMode = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            window.attributes.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }
        binding = ActivityVideoPlayerBinding.inflate(LayoutInflater.from(this))
        setContentView(R.layout.activity_video_player)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        mediaUri = intent.extras?.getString("mediaUri")
        mediaName = intent.extras?.getString("mediaName")
        setupUI()
    }
    private fun setupUI()
    {
        initializePlayer()
        findViewById<ImageView>(R.id.exo_rotateScreen).setOnClickListener {
            Toast.makeText(this,"Rotate Screen ", Toast.LENGTH_SHORT).show()
            setOrientation()
        }

        findViewById<ImageView>(R.id.exo_fullscreen).setOnClickListener {
            Toast.makeText(this,"Full Screen",Toast.LENGTH_SHORT).show()
            when(currentMode){
                0->{findViewById<PlayerView>(R.id.idVideoPlayerView).resizeMode = resignedMode[1]
                    currentMode++
                }
                1->{
                    findViewById<PlayerView>(R.id.idVideoPlayerView).resizeMode = resignedMode[2]
                    currentMode++
                }
                2->{
                    findViewById<PlayerView>(R.id.idVideoPlayerView).resizeMode = resignedMode[0]
                    currentMode=0
                }
            }
        }

        findViewById<TextView>(R.id.header_tv).text = mediaName
        findViewById<ImageView>(R.id.ic_cross_close_icon).setOnClickListener {
            player?.release()
            finish()
        }

    }
    private fun initializePlayer()
    {
        player = ExoPlayer.Builder(this).build()
        findViewById<PlayerView>(R.id.idVideoPlayerView).player = player
        mediaUri?.let {
            val mediaItem = MediaItem.fromUri(it)
            player?.setMediaItem(mediaItem)
            player?.prepare()
            player?.playWhenReady = true
        }
    }

    override fun onStop() {
        super.onStop()
        player?.pause()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        player?.release()
        finish()
    }
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus)
            hideSystemUI()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        hideSystemUI()
    }

    private fun hideSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                )
    }
    private fun setOrientation(){
        val orientation = this.resources.configuration.orientation
        if (orientation== ActivityInfo.SCREEN_ORIENTATION_PORTRAIT){
            this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            binding.idVideoPlayerView.layoutParams = ViewGroup.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.MATCH_PARENT)
        }else{
            this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            binding.idVideoPlayerView.layoutParams = ViewGroup.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.MATCH_PARENT)
        }
    }

}