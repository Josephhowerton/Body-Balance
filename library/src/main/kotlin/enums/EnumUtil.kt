package enums

inline fun <reified T : Enum<T>> safeEnumValueOf(type: String): T? {
    return try {
        enumValueOf<T>(type)
    } catch (e: IllegalArgumentException) {
        null
    }
}