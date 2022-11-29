package com.gamest.fastvideoplayer.presentation.video.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.gamest.fastvideoplayer.presentation.video.components.WidgetVideoItem
import com.gamest.fastvideoplayer.presentation.video.ui.custom.HomeContentLoading
import com.gamest.fastvideoplayer.presentation.video.ui.theme.FastVideoPlayerTheme
import com.gamest.fastvideoplayer.presentation.video.viewModel.VideoViewModel


@Composable
fun Videos(lazyListState: LazyListState){
    FastVideoPlayerTheme {
        val viewModel = hiltViewModel<VideoViewModel>()
        val list = viewModel.state.value
        val isLoading = viewModel.isLoading.value
        val openDialog = remember {
            mutableStateOf(false)
        }

        Column {
            Selector(
                onFilterSelect = {

                }
            )
            if (isLoading)
                HomeContentLoading()
            else
                LazyColumn(
                    state = lazyListState
                ) {
                    items(
                        items = list
                    ) { message ->
                        WidgetVideoItem(
                            videoItem = message,
                            onItemOption = {uri ->
                                openDialog.value = true
                            }
                        )
                        Spacer(modifier = Modifier
                            .height(5.dp)
                            .padding(1.dp)
                            .padding(vertical = 5.dp)
                            .background(color = Color.Red))
                    }
                }
            if (openDialog.value){
                CustomDialog(
                    onDismiss = {
                        openDialog.value = false
                    }
                )
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
fun CustomDialog(
    onDismiss : () -> Unit
){
    Dialog(
        onDismissRequest = {onDismiss()},
        content = {
            Surface(
                modifier = Modifier
                    .fillMaxWidth(),
                elevation = 4.dp,
                shape = RoundedCornerShape(5)
            ) {
                Column(
                    modifier = Modifier.padding(10.dp),
                ) {
                    Text(text = "Share to device", color = MaterialTheme.colors.onSurface)
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(text = "Delete" , color = MaterialTheme.colors.onSurface)
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(text = "Save to watch later", color = MaterialTheme.colors.onSurface)
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(text = "Play in background", color = MaterialTheme.colors.onSurface)
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(text = "Properties", color = MaterialTheme.colors.onSurface)
                }
            }
        }
    )
}

@Composable
fun Selector(
    onFilterSelect : (id:Int) -> Unit
){
    val list = mutableStateMapOf<Int, String>().also {
        it[0] = "Latest"
        it[1] = "Movie"
        it[2] = "Web Series"
        it[3] = "Videos"
        it[4] = "WhatsApp"
        it[5] = "Bing"
    }

    val current = mutableStateOf(0)

    LazyRow(
        modifier = Modifier.fillMaxWidth()
    ){
        itemsIndexed(items = list.toList()){ index, item ->
            val isSelected = current.value == index
            Box(
                modifier = Modifier
                    .padding(horizontal = 5.dp, vertical = 5.dp)
                    .background(
                        color = if (isSelected) MaterialTheme.colors.primary else MaterialTheme.colors.surface,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(horizontal = 10.dp, vertical = 5.dp)
                    .clickable {
                        current.value = index
                        onFilterSelect(index)
                    }
            ){
                Text(
                    text = item.second,
                    color = if (isSelected) Color.White else Color.Black,
                    fontSize = 16.sp
                )
            }
        }
    }

}