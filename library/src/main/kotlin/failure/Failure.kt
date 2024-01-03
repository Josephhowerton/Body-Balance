package failure

import com.fitness.resources.R

abstract class Failure: Throwable() {
    abstract val description: Int
    abstract val title: Int
}

data class AuthStateFailure(
    override val description: Int = R.string.desc_login_status_changed,
    override val title: Int = R.string.login_status_changed
): Failure()