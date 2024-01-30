package com.fitness.domain.model.nutrition

import cache.generateUniqueId
import enums.EMealType
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

data class Nutrition(
    val userId: String = "",
    val dateTime: String = "",
    val mealType: EMealType = EMealType.BREAKFAST,
    val recipe: Recipe = Recipe(),
    val recordId: String = generateUniqueId("$userId-$dateTime-${recipe.recipeId}"),
)


fun Nutrition.getDateAsLong(): Long {
    return try {
        val offsetDateTime = OffsetDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss XXX"))
        offsetDateTime.toInstant().toEpochMilli()
    } catch (e: DateTimeParseException) {
        0L
    }
}

fun Nutrition.getTimeFromDate(): Pair<Int, Int> {
    return try {
        val offsetDateTime = OffsetDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss XXX"))
        val time = offsetDateTime.toLocalTime()
        Pair(time.hour, time.minute)
    } catch (e: DateTimeParseException) {
        Pair(0, 0)
    }
}