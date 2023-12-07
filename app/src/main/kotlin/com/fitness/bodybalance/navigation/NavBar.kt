package com.fitness.bodybalance.navigation

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.fitness.navigation.BottomNavigationUtil

@Composable
fun NavBar(
    navController: NavHostController
){

    val backStackEntry = navController.currentBackStackEntryAsState()

    NavigationBar(containerColor = Color.Black) {
        BottomNavigationUtil.bottomNavItems.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            NavigationBarItem(
                selected = selected,
                onClick = { navController.navigate(item.route) },
                colors = NavigationBarItemDefaults.colors(indicatorColor = Color.Black),
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        modifier = Modifier.size(32.dp),
                        tint = Color.Blue,
                        contentDescription = "${item.name} Icon")
                }
            )
        }
    }
}