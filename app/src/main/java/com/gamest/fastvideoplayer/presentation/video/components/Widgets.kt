package com.gamest.fastvideoplayer.presentation.video.components

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.text.format.DateUtils
import android.util.Size
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import coil.compose.rememberAsyncImagePainter
import com.gamest.fastvideoplayer.R
import com.gamest.fastvideoplayer.data.model.Video
import com.gamest.fastvideoplayer.player.VideoPlayerActivity
import com.gamest.fastvideoplayer.presentation.video.ui.theme.FastVideoPlayerTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


fun getThumbnail(context:Context, uri:Uri) : Bitmap?{
    return try {
        context.contentResolver.loadThumbnail(uri, Size(480,320),null)
    }catch (e:Exception){
        null
    }
}

@Composable
fun WidgetVideoItem(
    videoItem: Video,
    onItemOption: (uri:String) -> Unit
){
    println("videoUri${videoItem.uri}")
    val activity = LocalContext.current
    FastVideoPlayerTheme() {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .background(color = Color.LightGray, shape = RoundedCornerShape(5))
                .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(5))
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onLongPress = {
                                println("longPress$it")
                                onItemOption(videoItem.uri)
                            },
                            onTap = {
//                                val intent = Intent(activity, VideoPlayerActivity::class.java)
//                                intent.putExtra("mediaUri",videoItem.uri)
//                                intent.putExtra("mediaName",videoItem.title)
//                                activity.startActivity(intent)
                            }
                        )
                    },
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                ){
                    Image(
                        painter = rememberAsyncImagePainter(
                            model = getThumbnail(LocalContext.current, videoItem.uri.toUri())?:R.drawable.app_icon,
                            placeholder = painterResource(id = R.drawable.app_icon)
                        ),
                        contentDescription = "thumbnail",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp),
                        contentScale = ContentScale.Fit
                    )
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(horizontal = 10.dp, vertical = 5.dp)
                            .background(shape = RoundedCornerShape(20), color = Color.Black)
                    ) {
                        Text(
                            text = DateUtils.formatElapsedTime(videoItem.duration.toLong()),
                            color = Color.White,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 3.dp),
                            fontSize = 12.sp
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .background(color = Color.White)
                        .padding(vertical = 3.dp, horizontal = 5.dp)
                ) {
                    Text(
                        text = videoItem.title,
                        fontSize = 14.sp,
                        fontFamily = FontFamily.SansSerif,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colors.onSurface
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun AudioPlayerView(){
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {

    }
}