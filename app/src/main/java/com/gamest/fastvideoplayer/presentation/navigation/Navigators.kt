package com.gamest.fastvideoplayer.presentation.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navOptions
import com.gamest.fastvideoplayer.R
import com.gamest.fastvideoplayer.presentation.video.screens.Music
import com.gamest.fastvideoplayer.presentation.video.screens.Videos
import com.gamest.fastvideoplayer.presentation.video.viewModel.VideoViewModel


@Composable
fun BottomNav(navController: NavController){
    val list = listOf(
        BottomNavItem.Videos,
        BottomNavItem.Music
    )

    BottomNavigation(
        backgroundColor = Color.Transparent,
        modifier = Modifier.background(color = Color.White),
        elevation = 0.dp
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        list.forEach { bottomNavItem ->
            BottomNavigationItem(
                selected = currentRoute == bottomNavItem.routeName,
                icon = {
                    when(bottomNavItem.routeName){
                        BottomNavItem.Videos.routeName -> {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(
                                        color = if (currentRoute.equals(BottomNavItem.Videos.routeName)) MaterialTheme.colors.primary else Color.White,
                                        shape = RoundedCornerShape(topEnd = 50.dp)
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(painter = painterResource(id = R.drawable.ic_player_icon),
                                    contentDescription = "",
                                    modifier = Modifier.size(40.dp)
                                )
                            }
                        }
                        BottomNavItem.Music.routeName -> {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(
                                        color = if (currentRoute.equals(BottomNavItem.Music.routeName)) MaterialTheme.colors.primary else Color.White,
                                        shape = RoundedCornerShape(topStart = 50.dp)
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(painter = painterResource(id = R.mipmap.icon_music),
                                    contentDescription = "",
                                    modifier = Modifier.size(40.dp)
                                )
                            }
                        }
                    }
                },
                onClick = {
                    navController.navigate(bottomNavItem.routeName, navOptions {
//                        AnimationUtils.currentAnimationTimeMillis()
                    })
                }
            )
        }
    }
}

@Composable
fun NavigationGraph(navController : NavHostController, lazyListState: LazyListState){
    NavHost(navController = navController, startDestination = BottomNavItem.Videos.routeName){
        composable(BottomNavItem.Videos.routeName ){
            Videos(lazyListState)
        }
        composable(BottomNavItem.Music.routeName){
            Music()
        }
    }
}



sealed class BottomNavItem(var title:String, icon: @Composable () -> Unit, var routeName:String){
    object Videos: BottomNavItem("videos", icon = { Icon(Icons.Filled.Home, contentDescription = "videos") }, "videos")
    object Music: BottomNavItem("music", icon = { Icon(Icons.Filled.Favorite, contentDescription = "music") }, "music")
//    object Favorite: BottomNavItem("Favorite", icon = { Icon(Icons.Filled.Home, contentDescription = "Favorite") }, "favorite")
//    object Profile: BottomNavItem("Profile", icon = { Icon(Icons.Filled.Home, contentDescription = "Profile") }, "profile")
}

sealed class FilterBy(var id:Int, var title: String){
    object All : FilterBy(0, "All")
    object Movie: FilterBy(1, "Movie")
    object WebSeries: FilterBy(2, "Web Series")
    object Videos: FilterBy(3, "Videos")
    object WhatsApp: FilterBy(4, "Whatsapp")
    object Bing: FilterBy(5, "Bing")
}