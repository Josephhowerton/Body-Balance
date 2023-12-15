package com.fitness.authentication.navigation

import androidx.hilt.navigation.compose.hiltViewModel
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
import extensions.cast
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
        navigation(startDestination = signUp, route = featureRoute) {
            composable(signUp) {
                val viewModel:SignUpViewModel = hiltViewModel()
                SignUpScreen(
                    onErrorEvent = { viewModel.handleError(it) },
                    onClickEmail = { navController.navigate(signUpEmail) },
                    onClickPhone = { navController.navigate(signUpPhone) },
                    onClickGoogle = {},
                    onClickFacebook = {},
                    onClickX = {},
                    onClickAnnotatedText = { navController.navigate(signInEmail) },
                )
            }

            composable(signUpEmail) {
                val viewModel:SignUpViewModel = hiltViewModel()
                SignUpEmailScreen(
                    state = viewModel.uiState.cast(),
                    onErrorEvent = {},
                    verifyCredentials = { viewModel.verifyCredentials(it) },
                    onClickContinue = {},
                )
            }

            composable(signUpPhone) {
                val viewModel:SignUpViewModel = hiltViewModel()
                SignUpPhoneScreen(
                    state = viewModel.uiState.cast(),
                    onErrorEvent = {},
                    verifyCredentials = {  viewModel.verifyCredentials(it) },
                    onClickContinue = {},
                )
            }

            composable(signInEmail) {
                val viewModel:SignInViewModel = hiltViewModel()
                SignInEmailScreen(
                    state = viewModel.uiState.cast(),
                    onErrorEvent = {},
                    verifyCredentials = { viewModel.verifyCredentials(it)  },
                    onClickPhone = { navController.navigate(signInPhone) },
                )
            }

            composable(signInPhone) {
                val viewModel:SignInViewModel = hiltViewModel()
                SignInPhoneScreen(
                    state = viewModel.uiState.cast(),
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