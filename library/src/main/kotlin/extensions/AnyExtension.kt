package extensions


inline fun <reified T : Any> Any.cast(): T {
    return this as T
}

fun <T> T.update(updateFn: T.() -> T): T {
    return updateFn()
}