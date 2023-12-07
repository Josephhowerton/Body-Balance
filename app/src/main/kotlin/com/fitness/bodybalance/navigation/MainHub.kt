package com.fitness.bodybalance.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import com.fitness.resources.R
import kotlinx.coroutines.launch
import com.fitness.navigation.DrawerItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainHub(
    items: List<DrawerItem>,
    navController: NavHostController,
    scope: CoroutineScope = rememberCoroutineScope(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
) {


    var bottomNavState by remember {
        mutableStateOf(true)
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModularDrawSheet(
                items,
                scope,
                drawerState
            ) {
                navController.navigate(it.route)
                bottomNavState = it.route == "home"
            }
        }
    ) {
        Scaffold(
            topBar = { TopAppBar(title = {}, navigationIcon = { NavDrawerIcon(scope, drawerState) }) },
            bottomBar = {
                AnimatedVisibility(visible = bottomNavState) {
                    NavBar(navController = navController)
                }

            }
        )
        { padding ->
            Box(modifier = Modifier.padding(padding)) {
                HomeNavigation(navController = navController)
            }
        }
    }
}

@Composable
fun HomeNavigation(navController: NavHostController) {

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModularDrawSheet(
    items: List<DrawerItem>,
    scope: CoroutineScope,
    drawerState: DrawerState,
    onSelectionChange: (DrawerItem) -> Unit
) {

    var selectedItem by remember { mutableStateOf(items[0]) }

    ModalDrawerSheet {
        Spacer(Modifier.height(12.dp))
        items.forEach { item ->
            NavigationDrawerItem(
                icon = {
                    Icon(
                        painterResource(id = item.icon),
                        contentDescription = stringResource(id = item.contentDescription),
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = { Text(item.name) },
                selected = item == selectedItem,
                onClick = {
                    scope.launch { drawerState.close() }

                    if (selectedItem != item) {
                        selectedItem = item
                        onSelectionChange(selectedItem)
                    }

                },
                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavDrawerIcon(
    scope: CoroutineScope,
    drawerState: DrawerState
) {
    IconButton(onClick = { scope.launch { drawerState.open() } }) {
        Icon(
            painter = painterResource(id = R.drawable.icon_menu),
            contentDescription = stringResource(id = R.string.content_description_menu),
            Modifier.size(24.dp)
        )
    }
}