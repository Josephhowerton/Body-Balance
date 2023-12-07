package com.fitness.navigation

import com.fitness.resources.R

object NavigationUtil {
    const val SettingsRoute =  "settings"
    const val ProfileRoute =  "home"
    const val HealthPlanningRoute =  "health_planning"
    const val HealthAnalysisRoute =  "health_analysis"

}

object BottomNavigationUtil{
    private const val TrainingHubRoute =  "training-hub"
    private const val HomeRoute =  "home"
    private const val WorkoutTrackingRoute =  "workout-tracking"
    private const val HealthAnalysisRoute =  "analysis"
    private const val HealthTrackingRoute =  "health-tracking"

    private const val TrainingHubTitle =  "Training Hub"
    private const val HomeTitle =  "Home"
    private const val WorkoutTracking =  "Workout Tracking"
    private const val HealthAnalysisTitle =  "Health Analysis"
    private const val HealthTrackingTitle =  "Health Tracking"

    val bottomNavItems = listOf(
        BottomNavItem(
            name = HealthAnalysisTitle,
            route = HealthAnalysisRoute,
            icon = R.drawable.icon_analysis
        ),
        BottomNavItem(
            name = HealthTrackingTitle,
            route = HealthTrackingRoute,
            icon = R.drawable.icon_diet_tracking
        ),
        BottomNavItem(name = HomeTitle, route = HomeRoute, icon = R.drawable.icon_home),
        BottomNavItem(
            name = WorkoutTracking,
            route = WorkoutTrackingRoute,
            icon = R.drawable.icon_laps
        ),
        BottomNavItem(
            name = TrainingHubTitle,
            route = TrainingHubRoute,
            icon = R.drawable.icon_sprint
        )
    )
}

object DrawerNavigationUtil{

    private const val AccountManagementRoute =  "account"
    private const val HomeRoute =  "home"
    private const val LogOutRoute =  "logout"
    private const val SettingsRoute =  "settings"

    private const val AccountManagementTitle =  "Account Management"
    private const val HomeTitle =  "Home"
    private const val SettingsTitle =  "Settings"
    private const val LogOutTitle =  "Log Out"

    val drawerNavItems = listOf(
        DrawerItem(
            name = HomeTitle,
            route = HomeRoute,
            icon = R.drawable.icon_home,
            contentDescription = R.string.content_description_home
        ),
        DrawerItem(
            name = SettingsTitle,
            route = SettingsRoute,
            icon = R.drawable.icon_person,
            contentDescription = R.string.content_description_settings
        ),
        DrawerItem(
            name = AccountManagementTitle,
            route = AccountManagementRoute,
            icon = R.drawable.icon_settings,
            contentDescription = R.string.content_description_account
        ),
        DrawerItem(
            name = LogOutTitle,
            route = LogOutRoute,
            icon = R.drawable.icon_settings,
            contentDescription = R.string.content_description_logout
        )
    )
}