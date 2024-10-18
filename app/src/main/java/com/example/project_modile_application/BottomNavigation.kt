package com.example.project_modile_application

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController


@Preview(showBackground = true)
@Composable
fun BottomNavigation() {
    val navController = rememberNavController()
    val items = listOf(
        Screen.Home,
        Screen.Search,
        Screen.Profile
    )

    Scaffold(
        bottomBar = {
            NavigationBar(
                modifier = Modifier
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
                containerColor = Color.White
            )
            {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                items.forEachIndexed { index, screen ->
                    NavigationBarItem(
                        selected = currentRoute == screen.route,
                        icon = {
                            when (screen) {
                                is Screen.Home -> Icon(
                                    painter = painterResource(id = R.drawable.iconhome),
                                    contentDescription = "Home",
                                    modifier = Modifier
                                        .size(24.dp)

                                )

                                is Screen.Search -> Icon(
                                    painter = painterResource(id = R.drawable.iconsearch),
                                    contentDescription = "Search",
                                    modifier = Modifier
                                        .size(24.dp)
                                )

                                is Screen.Profile -> Icon(
                                    painter = painterResource(id = R.drawable.iconperson),
                                    contentDescription = "Profile",
                                    modifier = Modifier
                                        .size(24.dp)
                                )
                            }
                        },
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = false
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            unselectedIconColor = Color(0xFF272727),
                            selectedIconColor = Color(0xFF3D3BFF),
                            unselectedTextColor = Color(0xFF272727),
                            selectedTextColor = Color(0xFF3D3BFF),
                            indicatorColor = Color.White
                        ),
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .shadow(
                    elevation = 8.dp,
                    shape = RectangleShape,
                    ambientColor = Color(0xFF3D3BFF),
                    spotColor = Color(0xFF3D3BFF)
                )
                .background(color = Color.White)
        ) {
            NavigationGraph(navController = navController)
        }
    }
}
