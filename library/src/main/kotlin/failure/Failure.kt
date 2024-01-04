package failure

import com.fitness.resources.R

abstract class Failure: Throwable() {
    abstract val description: Int
    abstract val title: Int
}

fun Throwable.toFailure(): Failure =
    if(this is Failure){
        this
    }
    else{
        GenericFailure()
    }

data class GenericFailure(
    override val description: Int = R.string.desc_generic_error,
    override val title: Int = R.string.title_generic_error
): Failure()

data class AuthStateFailure(
    override val description: Int = R.string.desc_login_status_changed,
    override val title: Int = R.string.login_status_changed
): Failure()


data class TimeoutCancellationFailure(
    override val description: Int = R.string.desc_connection_timeout,
    override val title: Int = R.string.title_connection_timeout
): Failure()