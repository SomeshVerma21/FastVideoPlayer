package com.gamest.fastvideoplayer.presentation.video.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gamest.fastvideoplayer.R
import com.gamest.fastvideoplayer.presentation.video.ui.theme.FastVideoPlayerTheme


@Composable
@Preview
fun VideoView(){
    FastVideoPlayerTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {

                }
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.app_icon),
                        contentDescription = "video thumbnail",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                    )
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(horizontal = 10.dp, vertical = 5.dp)
                            .background(shape = RoundedCornerShape(20), color = Color.Black)
                    ) {
                        Text(
                            text = "12:90",
                            color = Color.White,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 3.dp)
                        )
                    }
                }
                Box(
                    modifier = Modifier.fillMaxWidth()
                ){
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(imageVector = Icons.Filled.Person, contentDescription = "Profile")
                            Column(
                                verticalArrangement = Arrangement.SpaceAround
                            ) {
                                Text(
                                    text = "Video Title",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                                Text(
                                    text = "Video description lsdf jdsjfdsljflsdjfd jfdlsjd",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = Color.White
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}