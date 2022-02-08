package com.vermaji.fastvideoplayer

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.vermaji.fastvideoplayer.databinding.ActivityPlayerBinding
import java.io.File

class PlayerActivity : AppCompatActivity(),Player.Listener{
    private lateinit var binding:ActivityPlayerBinding
    private var player:ExoPlayer? =null

    private var mediaUri:String? = null
    private var mediaName:String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            window.attributes.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }
        binding = ActivityPlayerBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        mediaUri = intent.extras?.getString("mediaUri")
        mediaName = intent.extras?.getString("mediaName")
        setupUI()
    }
    private fun setupUI()
    {
        initializePlayer()
        findViewById<ImageView>(R.id.exo_fullscreen).setOnClickListener {
            Toast.makeText(this,"Full Screen Called ",Toast.LENGTH_SHORT).show()
            setOrientation()
        }
    }
    private fun initializePlayer()
    {
        player = ExoPlayer.Builder(this).build()
            .also {
                simpleExoPlayer ->
                binding.idVideoView.player = simpleExoPlayer
                mediaUri?.let {
                    val mediaItem = MediaItem.fromUri(it)
                    simpleExoPlayer.setMediaItem(mediaItem)
                    simpleExoPlayer.playWhenReady = true
                    simpleExoPlayer.play()
                }
            }
        player?.addListener(this)
        findViewById<TextView>(R.id.header_tv).text = mediaName
    }

    override fun onEvents(player: Player, events: Player.Events) {
        super.onEvents(player, events)
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

    override fun onPause() {
        super.onPause()
        player?.release()
    }

    override fun onResume() {
        super.onResume()
        initializePlayer()
    }
    override fun onBackPressed() {
        player?.release()
        super.onBackPressed()
        finish()
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
        if (orientation==ActivityInfo.SCREEN_ORIENTATION_PORTRAIT){
            this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            binding.idVideoView.layoutParams = ViewGroup.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.MATCH_PARENT)
        }else{
            this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR
            binding.idVideoView.layoutParams = ViewGroup.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.MATCH_PARENT)
        }
    }
}