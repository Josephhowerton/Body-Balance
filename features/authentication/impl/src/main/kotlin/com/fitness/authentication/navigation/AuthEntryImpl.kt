package com.fitness.authentication.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.fitness.authentication.AuthEntry
import com.fitness.authentication.home.view.`AcceptTermsAndPrivacy`
import com.fitness.authentication.home.viewmodel.HomeViewModel
import com.fitness.authentication.reset.view.SendPasswordResetScreen
import com.fitness.authentication.reset.viewmodel.ResetPasswordViewModel
import com.fitness.authentication.signin.view.SignInEmailScreen
import com.fitness.authentication.signin.view.SignInPhoneScreen
import com.fitness.authentication.signin.viewmodel.SignInViewModel
import com.fitness.authentication.signup.view.SignUpEmailScreen
import com.fitness.authentication.signup.view.SignUpPhoneScreen
import com.fitness.authentication.signup.view.SignUpScreen
import com.fitness.authentication.signup.viewmodel.SignUpViewModel

import com.fitness.component.screens.MessageScreen
import com.fitness.navigation.Destinations
import com.fitness.resources.R
import extensions.cast
import javax.inject.Inject

class AuthEntryImpl @Inject constructor() : AuthEntry() {

    companion object {
        const val termsAndPrivacy = "home"
        const val signInEmail = "sign_In_email"
        const val signInPhone = "sign_In_phone"
        const val signUp = "sign_up"
        const val signUpEmail = "sign_up_email"
        const val signUpPhone = "sign_up_phone"
        const val resetPassword = "reset_password"
        const val resetConfirmation = "reset_password_confirmation"
    }


    override fun NavGraphBuilder.navigation(
        navController: NavHostController,
        destinations: Destinations
    ) {
        navigation(startDestination = termsAndPrivacy, route = featureRoute) {
            composable(termsAndPrivacy) {
                val viewModel: HomeViewModel = hiltViewModel()
                AcceptTermsAndPrivacy(
                    state = viewModel.uiState.cast(),
                    onTriggerEvent = { viewModel.onTriggerEvent(it) },
                    onTriggerNavigation = {
                        navController.navigate(it) {
                            popUpTo(termsAndPrivacy) {
                                inclusive = true
                            }
                        }
                    }
                )
            }

            composable(signUp) {
                val viewModel: SignUpViewModel = hiltViewModel()
                SignUpScreen(
                    state = viewModel.uiState.cast(),
                    onPopBack = { navController.popBackStack() },
                    onTriggerNavigation = { navController.navigate(it) }
                )
            }

            composable(signUpEmail) {
                val viewModel: SignUpViewModel = hiltViewModel()
                SignUpEmailScreen(
                    state = viewModel.uiState.cast(),
                    onPopBack = { navController.popBackStack() },
                    onComplete = {},
                    onTriggerEvent = { viewModel.onTriggerEvent(it) },
                    onTriggerNavigation = { navController.navigate(it) { popUpTo(signUp) } }
                )
            }

            composable(signUpPhone) {
                val viewModel: SignUpViewModel = hiltViewModel()
                SignUpPhoneScreen(
                    state = viewModel.uiState.cast(),
                    onPopBack = { navController.popBackStack() },
                    onTriggerEvent = { viewModel.onTriggerEvent(it) },
                    onTriggerNavigation = {
                        navController.navigate(it) {
                            popUpTo(signUpPhone)
                        }
                    }
                )
            }

            composable(signInEmail) {
                val viewModel: SignInViewModel = hiltViewModel()
                SignInEmailScreen(
                    state = viewModel.uiState.cast(),
                    onPopBack = { navController.popBackStack() },
                    onTriggerEvent = { viewModel.onTriggerEvent(it) },
                    onTriggerNavigation = { navController.navigate(it) { popUpTo(signUp) } }
                )
            }

            composable(signInPhone) {
                val viewModel: SignInViewModel = hiltViewModel()
                SignInPhoneScreen(
                    state = viewModel.uiState.cast(),
                    onPopBack = { navController.popBackStack() },
                    onComplete = {},
                    onTriggerEvent = { viewModel.onTriggerEvent(it) },
                    onTriggerNavigation = { navController.navigate(it) { popUpTo(signInPhone) } }
                )
            }

            composable(resetPassword) {
                val viewModel: ResetPasswordViewModel = hiltViewModel()
                SendPasswordResetScreen(
                    state = viewModel.uiState.cast(),
                    onPopBack = { navController.popBackStack() },
                    onComplete = { navController.navigate(resetConfirmation) { popUpTo(signInEmail) } },
                    onTriggerEvent = { viewModel.onTriggerEvent(it) },
                )
            }

            composable(resetConfirmation) {
                MessageScreen(
                    message = R.string.desc_reset_password_email_confirmation,
                    onClick = { navController.navigate(signInEmail) }
                )
            }
        }
    }
}