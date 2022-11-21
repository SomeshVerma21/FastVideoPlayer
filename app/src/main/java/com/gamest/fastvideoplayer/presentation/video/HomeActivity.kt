package com.gamest.fastvideoplayer.presentation.video

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.gamest.fastvideoplayer.presentation.video.components.AppBar
import com.gamest.fastvideoplayer.presentation.video.components.HomeScreen
import com.gamest.fastvideoplayer.presentation.video.ui.theme.FastVideoPlayerTheme

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FastVideoPlayerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp(){
    Column() {
        AppBar()
        HomeScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FastVideoPlayerTheme {
        MyApp()
    }
}