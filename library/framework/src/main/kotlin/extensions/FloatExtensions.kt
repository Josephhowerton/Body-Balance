package extensions

fun Float.format(length: Int) = String.format("%.${length}f", this)