class SignUpEntry : AggregateFeatureEntry {
    override val featureRoute: String get() = "sign_up"

    override fun androidx.navigation.NavGraphBuilder.navigation(
        navController: androidx.navigation.NavHostController,
        destinations: Destinations
    ) {
        TODO("Not yet implemented")
    }

}