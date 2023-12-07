package com.fitness.authentication.navigation

import com.fitness.authentication.AuthEntry
import com.fitness.navigation.Destinations
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.fitness.authentication.forgotpassword.view.ForgotPasswordScreen
import com.fitness.authentication.signin.view.SignInScreen
import com.fitness.authentication.signup.view.SignUpScreen
import javax.inject.Inject

class AuthEntryImpl @Inject constructor(): AuthEntry() {

    private val signIn = "sign_in"
    private val signUp = "sign_up"
    private val forgotPassword = "forgot_password"

    override fun NavGraphBuilder.navigation(navController: NavHostController, destinations: Destinations) {
        navigation(startDestination = signIn, route = featureRoute){
            composable(signIn) {
                SignInScreen()
            }
            composable(signUp) {
                SignUpScreen()
            }
            composable(forgotPassword) {
                ForgotPasswordScreen()
            }
        }
    }
}