package com.gamest.fastvideoplayer.presentation.home.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.gamest.fastvideoplayer.R
import com.gamest.fastvideoplayer.presentation.home.media.ui.theme.FastVideoPlayerTheme


@Composable
@Preview
fun PreviewApp(){
    FastVideoPlayerTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
            ) {
                PlayerExtended()
            }

        }
    }
}

@Composable
fun MusicScreen(){
    FastVideoPlayerTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
            ) {
                PlayerExtended()
            }

        }

    }
}




@Composable
fun PlayerExtended(){
    var isExtended by remember {
        mutableStateOf(false)
    }

    var mediaProgress by remember {
        mutableStateOf(0.2f)
    }

    if (isExtended)
        AnimatedVisibility(
            visible = isExtended,
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = MaterialTheme.colors.surface)
                ) {
                    // top navigation
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowLeft,
                            contentDescription = "left navigation",
                            modifier = Modifier
                                .size(40.dp)
                                .rotate(-90F)
                                .clickable {
                                    isExtended = !isExtended
                                },
                            tint = MaterialTheme.colors.primary
                        )
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = "Top most favorite title song ever",
                            fontSize = 18.sp,
                            fontFamily = FontFamily.SansSerif,
                            textAlign = TextAlign.Center,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 20.dp, end = 20.dp, top = 20.dp)
                        )
                        Text(
                            text = "Artiest name here",
                            fontSize = 13.sp,
                            fontFamily = FontFamily.SansSerif,
                            textAlign = TextAlign.Center,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 40.dp, end = 40.dp, top = 5.dp)
                        )

                        Image(
                            painter = rememberAsyncImagePainter(model = R.drawable.thumbnail),
                            contentDescription = "thumbnail",
                            modifier = Modifier
                                .size(300.dp)
                                .shadow(10.dp, RoundedCornerShape(10))
                                .padding(top = 20.dp)
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp)
                                .padding(top = 20.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "01:02")
                            Slider(
                                value = mediaProgress,
                                onValueChange = {
                                    mediaProgress = it
                                },
                                modifier = Modifier.width(250.dp)
                            )
                            Text(text = "01:02")
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 30.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Box(
                                modifier = Modifier
                                    .background(
                                        color = MaterialTheme.colors.primary,
                                        shape = RoundedCornerShape(50)
                                    )
                                    .padding(10.dp)
                            ){
                                Image(
                                    painter = rememberAsyncImagePainter(model = R.drawable.next),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(30.dp)
                                        .rotate(180F),
                                    contentScale = ContentScale.Crop
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .background(
                                        color = MaterialTheme.colors.primary,
                                        shape = RoundedCornerShape(50)
                                    )
                                    .padding(10.dp)
                            ){
                                Image(
                                    painter = rememberAsyncImagePainter(model = R.drawable.play_button),
                                    contentDescription = "",
                                    modifier = Modifier.size(30.dp)
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .background(
                                        color = MaterialTheme.colors.primary,
                                        shape = RoundedCornerShape(50)
                                    )
                                    .padding(10.dp)
                            ){
                                Image(
                                    painter = rememberAsyncImagePainter(model = R.drawable.next),
                                    contentDescription = "",
                                    modifier = Modifier.size(30.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    else
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(color = Color.Red)
                .padding(10.dp)
                .clickable {
                    isExtended = !isExtended
                }
        ){
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(painter = painterResource(id = R.drawable.thumbnail), contentDescription = "small icon")
                Row(
                    modifier = Modifier
                ) {
                    Box(
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colors.primary,
                                shape = RoundedCornerShape(50)
                            )
                            .padding(10.dp)
                    ){
                        Image(
                            painter = rememberAsyncImagePainter(model = R.drawable.next),
                            contentDescription = "",
                            modifier = Modifier
                                .size(30.dp)
                                .rotate(180F),
                            contentScale = ContentScale.Crop
                        )
                    }
                    Box(
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colors.onBackground,
                                shape = RoundedCornerShape(50)
                            )
                            .padding(5.dp)
                    ){
                        Image(
                            painter = rememberAsyncImagePainter(model = R.drawable.play_button),
                            contentDescription = "",
                            modifier = Modifier.size(30.dp)
                        )
                    }
                    Box(
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colors.primary,
                                shape = RoundedCornerShape(50)
                            )
                            .padding(10.dp)
                    ){
                        Image(
                            painter = rememberAsyncImagePainter(model = R.drawable.next),
                            contentDescription = "",
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }
            }
        }
}