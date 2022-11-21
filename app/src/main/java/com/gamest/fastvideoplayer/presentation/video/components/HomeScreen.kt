package com.gamest.fastvideoplayer.presentation.video.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gamest.fastvideoplayer.presentation.video.ui.theme.FastVideoPlayerTheme


@Composable
@Preview()
fun HomeScreen(){
    FastVideoPlayerTheme {
        LazyColumn{
            items(10){
                VideoView()
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}