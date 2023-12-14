package com.fitness.authentication.navigation

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.fitness.authentication.AuthEntry
import com.fitness.authentication.reset.view.ForgotPasswordScreen
import com.fitness.authentication.signin.view.SignInEmailScreen
import com.fitness.authentication.signin.view.SignInPhoneScreen
import com.fitness.authentication.signin.viewmodel.SignInViewModel
import com.fitness.authentication.signup.view.SignUpEmailScreen
import com.fitness.authentication.signup.view.SignUpPhoneScreen
import com.fitness.authentication.signup.view.SignUpScreen
import com.fitness.authentication.signup.viewmodel.SignUpViewModel
import com.fitness.navigation.Destinations
import javax.inject.Inject

class AuthEntryImpl @Inject constructor() : AuthEntry() {

    private val signInEmail = "sign_In_email"
    private val signInPhone = "sign_In_phone"
    private val signUp = "sign_up"
    private val signUpEmail = "sign_up_email"
    private val signUpPhone = "sign_up_phone"
    private val forgotPassword = "forgot_password"


    override fun NavGraphBuilder.navigation(
        navController: NavHostController,
        destinations: Destinations
    ) {
        val signUpBackAction = { navController.popBackStack(signUp, inclusive = false) }
        val signInBackAction = { navController.popBackStack(signUp, inclusive = false) }

        navigation(startDestination = signUp, route = featureRoute) {
            composable(signUp) {
                val viewModel: SignUpViewModel = viewModel()
                SignUpScreen(
                    onErrorEvent = {},
                    onClickEmail = { navController.navigate(signUpEmail) },
                    onClickPhone = { navController.navigate(signUpPhone) },
                    onClickGoogle = {},
                    onClickFacebook = {},
                    onClickX = {},
                    onClickAnnotatedText = { navController.navigate(signInEmail) },
                )
            }

            composable(signUpEmail) {
                val viewModel: SignUpViewModel = viewModel()
                SignUpEmailScreen(
                    onErrorEvent = {},
                    verifyCredentials = { viewModel.verifyCredentials(it) },
                    onClickContinue = {},
                )
            }

            composable(signUpPhone) {
                val viewModel: SignUpViewModel = viewModel()
                SignUpPhoneScreen(
                    onErrorEvent = {},
                    verifyCredentials = { viewModel.verifyCredentials(it)  },
                    onClickContinue = {},
                )
            }

            composable(signInEmail) {
                val viewModel: SignInViewModel = viewModel()
                SignInEmailScreen(
                    onErrorEvent = {},
                    verifyCredentials = { viewModel.verifyCredentials(it)  },
                    onClickPhone = { navController.navigate(signInPhone) },
                )
            }

            composable(signInPhone) {
                val viewModel: SignInViewModel = viewModel()
                SignInPhoneScreen(
                    onErrorEvent = {},
                    verifyCredentials = { viewModel.verifyCredentials(it) },
                    onClickEmail = { navController.navigate(signInEmail) },
                )
            }

            composable(forgotPassword) {
                ForgotPasswordScreen(
                    onErrorEvent = {},
                )
            }
        }
    }
}