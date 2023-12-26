package com.fitness.bodybalance.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.fitness.authentication.AuthEntry
import com.fitness.authentication.manager.AuthState
import com.fitness.bodybalance.di.AppProvider
import com.fitness.dashboard.DashboardEntry
import com.fitness.theme.ui.BodyBalanceTheme
import com.fitness.navigation.BottomNavigationUtil
import com.fitness.navigation.Destinations
import com.fitness.navigation.DrawerItem
import com.fitness.navigation.DrawerNavigationUtil
import com.fitness.navigation.find
import com.fitness.onboard.OnboardEntry
import com.fitness.welcome.WelcomeEntry
import com.fitness.resources.R
import com.fitness.theme.AppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun AppContent(
    authState: State<AuthState>,
    appTheme: State<AppTheme>,
    appProvider: AppProvider
) {

    BodyBalanceTheme(appTheme = appTheme) {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.surface) {
            AppHub(authState, appProvider)
        }
    }
}


@Composable
fun AppHub(
    authState: State<AuthState>,
    appProvider: AppProvider,
    items: List<DrawerItem> = DrawerNavigationUtil.drawerNavItems,
    scope: CoroutineScope = rememberCoroutineScope(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
) {
    val navController = rememberNavController()
    var bottomNavState by remember {
        mutableStateOf(true)
    }

    if (authState.value == AuthState.Authenticated) {

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
            }) {
            MainHub(
                bottomNavState = bottomNavState,
                destinations = appProvider.destinations
            )
        }
    } else {
        OnboardingAuthenticationHub(
            destinations = appProvider.destinations
        )
    }
}


@Composable
fun MainHub(
    bottomNavState: Boolean,
    destinations: Destinations,
    navController: NavHostController = rememberNavController(),
) {
    Scaffold(
        topBar = {},
        bottomBar = {
            AnimatedVisibility(visible = bottomNavState) {
                NavBar(navController = navController)
            }
        }
    ) { innerPadding ->
        val dashboard = destinations.find<DashboardEntry>()
        NavHost(navController = navController, startDestination = dashboard.featureRoute, modifier = Modifier.padding(innerPadding)) {
            with(dashboard){
                composable(navController, destinations)
            }
        }
    }
}

@Composable
fun OnboardingAuthenticationHub(
    destinations: Destinations,
    navController: NavHostController = rememberNavController(),
) {

    val welcomeEntry = destinations.find<WelcomeEntry>()
    val onboardEntry = destinations.find<OnboardEntry>()
    val authEntry = destinations.find<AuthEntry>()
    NavHost(navController, startDestination = welcomeEntry.featureRoute) {

        with(welcomeEntry) {
            composable(navController, destinations)
        }

        with(onboardEntry){
            navController.popBackStack(authEntry.featureRoute, false)
            navigation(navController, destinations)
        }

        with(authEntry) {
            navController.popBackStack(welcomeEntry.featureRoute, false)
            navigation(navController, destinations)
        }
    }
}

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