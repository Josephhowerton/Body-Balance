package failure

import com.google.firebase.auth.FirebaseAuthActionCodeException
import com.google.firebase.auth.FirebaseAuthEmailException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthMissingActivityForRecaptchaException
import com.google.firebase.auth.FirebaseAuthMultiFactorException
import com.google.firebase.auth.FirebaseAuthRecentLoginRequiredException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWebException
import com.fitness.resources.R

/**
 * @param description description of error displayed to user
 * @param title title of error displayed to user
 */

sealed class AuthFailure : Failure() {
    data class UserError(override val description: Int, override val title: Int): AuthFailure()
    data class SystemError(override val description: Int, override val title: Int): AuthFailure()
    data class UnknownError(override val description: Int, override val title: Int): AuthFailure()
}

/**
 * @see FirebaseAuthActionCodeException Represents the exception which is a result of an expired or an invalid out of band code.
 * @see FirebaseAuthEmailException Represents the exception which is a result of an attempt to send an email via Firebase Auth (e.g. a password reset email)
 * @see FirebaseAuthInvalidCredentialsException Thrown when one or more of the credentials passed to a method fail to identify and/or authenticate the user subject of that operation.
 * @see FirebaseAuthInvalidUserException Thrown when performing an operation on a FirebaseUser instance that is no longer valid.
 * @see FirebaseAuthMissingActivityForRecaptchaException Thrown when the auth request attempted to fetch a reCAPTCHA token, but the activity is missing or null.
 * @see FirebaseAuthMultiFactorException This exception is returned when a user that previously enrolled a second factor tries to sign in and passes the first factor successfully.
 * @see FirebaseAuthRecentLoginRequiredException Thrown on security sensitive operations on a FirebaseUser instance that require the user to have signed in recently, when the requirement isn't met.
 * @see FirebaseAuthUserCollisionException Thrown when an operation on a FirebaseUser instance couldn't be completed due to a conflict with another existing user.
 * @see FirebaseAuthWeakPasswordException Thrown when using a weak password (less than 6 chars) to create a new account or to update an existing account's password.
 * @see FirebaseAuthWebException Thrown when a web operation couldn't be completed.
 */
fun Throwable.handleAuthFailure(): AuthFailure {
    return when (this) {
        is FirebaseAuthInvalidCredentialsException -> {
            this.errorCode
            AuthFailure.UserError(
                title = R.string.incorrect_credentials,
                description = R.string.invalid_credentials_desc
            )
        }

        is FirebaseAuthInvalidUserException -> {
            AuthFailure.UserError(
                title = R.string.invalid_user,
                description = R.string.invalid_user_desc
            )
        }

        is FirebaseAuthUserCollisionException -> {
            handleUserCollisionException(this.errorCode)
        }

        is FirebaseAuthActionCodeException -> {
            AuthFailure.SystemError(
                title = R.string.we_are_sorry,
                description = R.string.default_error_desc
            )
        }

        is FirebaseAuthEmailException -> {
            AuthFailure.SystemError(
                title = R.string.we_are_sorry,
                description = R.string.default_error_desc
            )
        }

        is FirebaseAuthMissingActivityForRecaptchaException -> {
            AuthFailure.SystemError(
                title = R.string.we_are_sorry,
                description = R.string.default_error_desc
            )
        }

        is FirebaseAuthMultiFactorException -> {
            AuthFailure.SystemError(
                title = R.string.we_are_sorry,
                description = R.string.default_error_desc
            )
        }

        is FirebaseAuthRecentLoginRequiredException -> {
            AuthFailure.SystemError(
                title = R.string.we_are_sorry,
                description = R.string.default_error_desc
            )
        }

        is FirebaseAuthWebException -> {
            AuthFailure.SystemError(
                title = R.string.we_are_sorry,
                description = R.string.default_error_desc
            )
        }

        else -> {
            AuthFailure.UnknownError(
                title = R.string.we_are_sorry,
                description = R.string.default_error_desc
            )
        }
    }
}

private fun handleUserCollisionException(code: String): AuthFailure {
    return if(code == AuthUserCollisionCodes.ERROR_EMAIL_ALREADY_IN_USE.name){
        AuthFailure.SystemError(
            title = R.string.user_exist_email,
            description = R.string.user_collision_email_desc
        )
    } else {
        AuthFailure.SystemError(
            title = R.string.user_exist_phone,
            description = R.string.user_collision_phone_desc
        )
    }
}