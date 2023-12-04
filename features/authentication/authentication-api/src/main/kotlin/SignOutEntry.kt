class SignOutEntry: AggregateFeatureEntry {
    override val featureRoute: String get() = "sign_out"
    override fun androidx.navigation.NavGraphBuilder.navigation(
        navController: androidx.navigation.NavHostController,
        destinations: Destinations
    ) {
        TODO("Not yet implemented")
    }
}