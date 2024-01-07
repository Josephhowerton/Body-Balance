package com.fitness.bodybalance.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
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
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.fitness.authentication.AuthEntry
import com.fitness.authentication.manager.AuthenticationState
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
import com.fitness.signout.SignOutEntry
import com.fitness.theme.AppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@Composable
fun AppContent(
    authStateFlow: StateFlow<AuthenticationState>,
    appTheme: State<AppTheme>,
    showMainHubAnimation: Boolean,
    appProvider: AppProvider
) {

    BodyBalanceTheme(appTheme = appTheme) {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.surface) {
            AppHub(authStateFlow, appProvider, showMainHubAnimation)
        }
    }
}


@Composable
fun AppHub(
    authStateFlow: StateFlow<AuthenticationState>,
    appProvider: AppProvider,
    showWelcomeAnimation: Boolean
) {
    val authState by authStateFlow.collectAsState()

    Crossfade(targetState = authState, label = "") {
        when(it){
            is AuthenticationState.Authenticated -> {
                MainHub(showMainHubAnimation = showWelcomeAnimation, destinations = appProvider.destinations,)
            }
            is AuthenticationState.UnAuthenticated -> {
                AuthenticationHub(destinations = appProvider.destinations)
            }
            is AuthenticationState.OnBoard -> {
                OnboardHub(destinations = appProvider.destinations)
            }
        }
    }
}

@Composable
fun MainHub(
    showMainHubAnimation: Boolean,
    destinations: Destinations,
    scope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
    items: List<DrawerItem> = DrawerNavigationUtil.drawerNavItems,
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
) {
    var bottomNavState by remember { mutableStateOf(true) }
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModularDrawSheet(items, scope, drawerState) { item ->
                navController.navigate(item.route)
                bottomNavState = !showMainHubAnimation
            }
        }) {
        MainHubNavigation(
            bottomNavState = bottomNavState,
            showMainHubAnimation = showMainHubAnimation,
            destinations = destinations,
            scope = scope,
            drawerState = drawerState,
            navController = navController
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainHubNavigation(
    bottomNavState: Boolean,
    showMainHubAnimation: Boolean,
    destinations: Destinations,
    scope: CoroutineScope,
    drawerState: DrawerState,
    navController: NavHostController,
) {
    Scaffold(
        topBar = { TopAppBar(title = {}, navigationIcon = { NavDrawerIcon(scope, drawerState) }) },
        bottomBar = {
            AnimatedVisibility(visible = bottomNavState) {
                NavBar(navController = navController)
            }
        }
    ) { innerPadding ->

        val welcome = destinations.find<WelcomeEntry>()
        val dashboard = destinations.find<DashboardEntry>()
        val signout = destinations.find<SignOutEntry>()
        val startDestination = if(showMainHubAnimation) welcome else dashboard
        NavHost(navController = navController, startDestination = startDestination.featureRoute, modifier = Modifier.padding(innerPadding)) {
            with(welcome){
                composable(navController, destinations)
            }

            with(dashboard){
                composable(navController, destinations)
            }

            with(signout){
                composable(navController, destinations)
            }
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
@Composable
fun AuthenticationHub(
    destinations: Destinations,
    navController: NavHostController = rememberNavController(),
) {
    val welcomeEntry = destinations.find<WelcomeEntry>()
    val authEntry = destinations.find<AuthEntry>()

    NavHost(navController, startDestination = authEntry.featureRoute) {

        with(welcomeEntry) {
            composable(navController, destinations)
        }

        with(authEntry) {
            navController.popBackStack(welcomeEntry.featureRoute, false)
            navigation(navController, destinations)
        }
    }
}

@Composable
fun OnboardHub(
    destinations: Destinations,
    navController: NavHostController = rememberNavController(),
) {

    val onboardEntry = destinations.find<OnboardEntry>()

    NavHost(navController, startDestination = onboardEntry.featureRoute) {
        with(onboardEntry){
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