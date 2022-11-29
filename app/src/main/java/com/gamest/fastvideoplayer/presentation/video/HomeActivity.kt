package com.gamest.fastvideoplayer.presentation.video

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.gamest.fastvideoplayer.presentation.navigation.BottomNav
import com.gamest.fastvideoplayer.presentation.navigation.NavigationGraph
import com.gamest.fastvideoplayer.presentation.video.components.AppBar
import com.gamest.fastvideoplayer.presentation.video.ui.theme.FastVideoPlayerTheme
import com.gamest.fastvideoplayer.presentation.video.viewModel.VideoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = hiltViewModel<VideoViewModel>()
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

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MyApp(){
    val state = rememberScaffoldState()
    val navController = rememberNavController()
    val lazyListState = rememberLazyListState()
    Scaffold(
        scaffoldState = state,
//        drawerContent = { LeftDrawer() },
        drawerElevation = 10.dp,
        topBar = { AppBar(state, navController, lazyListState) },
        modifier = Modifier.shadow(20.dp, RectangleShape),
        bottomBar = { BottomNav(navController = navController)},
    ) {
        NavigationGraph(navController = navController, lazyListState)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FastVideoPlayerTheme {
        MyApp()
    }
}