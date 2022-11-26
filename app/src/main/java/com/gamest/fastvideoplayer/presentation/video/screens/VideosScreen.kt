package com.gamest.fastvideoplayer.presentation.video.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gamest.fastvideoplayer.presentation.video.components.WidgetVideoItem
import com.gamest.fastvideoplayer.presentation.video.ui.theme.FastVideoPlayerTheme
import com.gamest.fastvideoplayer.presentation.video.viewModel.VideoViewModel


@Composable
fun Videos(lazyListState: LazyListState){
    FastVideoPlayerTheme {
        val list = hiltViewModel<VideoViewModel>().state.value
        Column {
            Selector()
            LazyColumn(
                state = lazyListState
            ) {
                items(
                    items = list
                ) { message ->
                    WidgetVideoItem(videoItem = message)
                    Spacer(modifier = Modifier
                        .height(5.dp)
                        .padding(1.dp)
                        .padding(vertical = 5.dp)
                        .background(color = Color.Red))
                }
            }
        }

    }
}

@Composable
fun LazyListState.isScrollingDown(): Boolean{
    var previousIndex by remember(this) { mutableStateOf(firstVisibleItemIndex) }
    var previousScrollOffset by remember(this) { mutableStateOf(firstVisibleItemScrollOffset) }
    return remember(this) {
        derivedStateOf {
            if (previousIndex != firstVisibleItemIndex) {
                previousIndex > firstVisibleItemIndex
            } else {
                previousScrollOffset >= firstVisibleItemScrollOffset
            }.also {
                previousIndex = firstVisibleItemIndex
                previousScrollOffset = firstVisibleItemScrollOffset
            }
        }
    }.value
}

@Composable
@Preview
fun Selector(){
    val list = listOf(5)

    LazyRow(
        modifier = Modifier.fillMaxWidth()
    ){
        items(items = list){
            var isSelected by remember {
                mutableStateOf(false)
            }
            Box(
                modifier = Modifier
                    .background(
                        color = if (isSelected) MaterialTheme.colors.primary else MaterialTheme.colors.surface,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(horizontal = 10.dp, vertical = 5.dp)
                    .clickable {
                        isSelected = !isSelected
                    }
            ){
                Text(
                    text = "Latest",
                    color = if (isSelected) Color.White else Color.Black,
                    fontSize = 16.sp
                )
            }
        }
    }

}