package auth

sealed class PhoneAuthState {
    object Idle : PhoneAuthState()
    object Verified: PhoneAuthState()
    data class CodeSent(val verificationId: String) : PhoneAuthState()
    data class Error(val exception: Exception) : PhoneAuthState()
}