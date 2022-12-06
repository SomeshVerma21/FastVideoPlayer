package com.gamest.fastvideoplayer.presentation.home.media.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.gamest.fastvideoplayer.R
import com.gamest.fastvideoplayer.presentation.home.screens.isScrollingDown
import com.gamest.fastvideoplayer.presentation.home.media.ui.theme.FastVideoPlayerTheme


@Composable
fun AppBar(scaffoldState: ScaffoldState, navController: NavHostController, lazyListState: LazyListState){
    FastVideoPlayerTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = if (lazyListState.isScrollingDown()) 50.dp else 0.dp)
                .animateContentSize(animationSpec = tween(300))
                .background(color = MaterialTheme.colors.primary)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.app_icon),
                        contentDescription = "app icon",
                        modifier = Modifier
                            .height(40.dp)
                            .background(shape = RoundedCornerShape(80), color = Color.Transparent),
                    )
                    Text(
                        text = "Fast Player",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif,
                        color = MaterialTheme.colors.onSurface,
                        modifier = Modifier
                            .padding(0.dp)
                            .padding(start = 10.dp)
                    )
                }
                Row(
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_search_icon),
                        contentDescription = "search icon",
                        modifier = Modifier.clickable {  },
                        colorFilter = ColorFilter.tint(color = MaterialTheme.colors.onSurface)
                    )
                }
            }
        }
    }
}