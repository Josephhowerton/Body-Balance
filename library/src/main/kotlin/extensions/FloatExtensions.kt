package extensions

fun Float.format(length: Int) = String.format("%.${length}f", this)

fun Float.toFeetAndInches(): Pair<Int, Int> {
    val totalInches = this / 2.54f
    val feet = (totalInches / 12).toInt()
    val remainingInches = (totalInches % 12).toInt()

    return Pair(feet, remainingInches)
}