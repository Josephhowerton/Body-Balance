package failure

abstract class Failure: Throwable() {
    abstract val description: Int
    abstract val title: Int
}