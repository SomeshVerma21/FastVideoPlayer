package com.gamest.fastvideoplayer.player

import android.content.Context
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.media.AudioManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.OpenableColumns
import android.view.*
import android.widget.ImageView
import android.widget.SeekBar
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
    private lateinit var audioManager:AudioManager
    private var currentMode = 0
    private lateinit var volumeSeekbaar:SeekBar
    private lateinit var volumeIcon:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            window.attributes.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }
        binding = ActivityVideoPlayerBinding.inflate(LayoutInflater.from(this))
        setContentView(R.layout.activity_video_player)
        audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        volumeSeekbaar = findViewById<SeekBar>(R.id.idVolumeSeekbar)
        volumeIcon = findViewById<ImageView>(R.id.audioOption)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        val data: Uri? = intent?.data
        if (data!=null){
            mediaUri = data.toString()
            mediaName = data.getOriginalFileName(this)
        }else{
            mediaUri = intent.extras?.getString("mediaUri")
            mediaName = intent.extras?.getString("mediaName")
        }
        setupUI()
    }
    private fun setupUI()
    {
        initializePlayer()
        findViewById<ImageView>(R.id.exo_rotateScreen).setOnClickListener {
            setOrientation()
        }

        findViewById<ImageView>(R.id.exo_fullscreen).setOnClickListener {
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
        volumeSeekbaar.max = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
        volumeSeekbaar.progress = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
        volumeSeekbaar.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,p1,0)
                if (audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)==0){
                    volumeIcon.setImageResource(R.drawable.ic_volume_off)
                }else{
                    volumeIcon.setImageResource(R.drawable.ic_volume_on)
                }
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {
            }
            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode==KeyEvent.KEYCODE_VOLUME_DOWN||keyCode==KeyEvent.KEYCODE_VOLUME_UP){
             volumeSeekbaar.progress = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
            if (audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)==0){
                volumeIcon.setImageResource(R.drawable.ic_volume_off)
            }else{
                volumeIcon.setImageResource(R.drawable.ic_volume_on)
            }
        }
        return super.onKeyDown(keyCode, event)
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

    fun Uri.getOriginalFileName(context: Context): String? {
        return context.contentResolver.query(this, null, null, null, null)?.use {
            val nameColumnIndex = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            it.moveToFirst()
            it.getString(nameColumnIndex)
        }
    }

}