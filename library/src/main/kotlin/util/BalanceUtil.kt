package util

import java.time.Instant
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun convertToDateTimeString(dateLong: Long, hour: Int, minute: Int): String {
    // Convert the Long timestamp to LocalDate
    val localDate = Instant.ofEpochMilli(dateLong).atZone(ZoneId.systemDefault()).toLocalDate()

    // Create a LocalTime from the hour and minute
    val localTime = LocalTime.of(hour, minute)

    // Combine LocalDate and LocalTime to get ZonedDateTime
    val zonedDateTime = ZonedDateTime.of(localDate, localTime, ZoneId.systemDefault())

    // Format ZonedDateTime to a String
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss Z")

    return zonedDateTime.format(formatter)
}

fun convertLongToDate(timeInMillis: Long): String {
    val formatter = DateTimeFormatter.ofPattern("EEEE, MMMM d", Locale.ENGLISH)
    return Instant.ofEpochMilli(timeInMillis).atZone(ZoneId.systemDefault()).format(formatter)
}
fun formatTimeWithAmPm(hour: Int, minute: Int): String {
    val amPm = if (hour < 12) "AM" else "PM"
    val formattedHour = when {
        hour == 0 -> 12
        hour > 12 -> hour - 12
        else -> hour
    }
    val formattedMinute = minute.toString().padStart(2, '0')
    return "$formattedHour:$formattedMinute $amPm"
}
