package com.fitness.authentication.home.viewmodel

enum class CheckBoxState{
    NONE,
    ERROR,
    OK
}

enum class Direction{
    NONE,
    SIGN_IN,
    SIGN_UP
}
data class HomeState(
    val hasAgreed: Boolean = false,
    val checkBoxState: CheckBoxState = CheckBoxState.NONE,
    val direction: Direction = Direction.NONE
)

sealed class HomeEvent {

    data class CheckBoxChanged(val newState: Boolean): HomeEvent()
    data class SignIn(val hasAgreed: Boolean): HomeEvent()
    data class SignUp(val hasAgreed: Boolean): HomeEvent()
}