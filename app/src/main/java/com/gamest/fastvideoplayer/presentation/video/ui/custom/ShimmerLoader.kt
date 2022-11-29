package com.gamest.fastvideoplayer.presentation.video.ui.custom

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview
@Composable
fun HomeContentLoading(){
    val infiniteTransition = rememberInfiniteTransition()
    val offset by infiniteTransition.animateFloat(
        initialValue = 0F,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(tween(1000, easing = LinearEasing))
    )
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
                .padding(10.dp)
        ) {
            items(5){
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    Color.Gray,
                                    Color.LightGray,
                                    Color.Gray
                                ),
                                start = Offset.Zero,
                                end = Offset(offset + 1000f, offset + 800),
                                tileMode = TileMode.Mirror
                            ),
                            shape = RoundedCornerShape(10)
                        )
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}