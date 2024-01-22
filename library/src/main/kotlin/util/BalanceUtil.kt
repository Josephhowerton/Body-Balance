package util

import java.time.Instant
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun convertToDateTimeString(dateLong: Long, hour: Int, minute: Int): String {

    val localDate = Instant.ofEpochMilli(dateLong).atZone(ZoneId.systemDefault()).toLocalDate()
    val localTime = LocalTime.of(hour, minute)
    val zonedDateTime = ZonedDateTime.of(localDate, localTime, ZoneId.systemDefault())

    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss Z")

    return zonedDateTime.format(formatter)
}

fun convertLongToDate(timeInMillis: Long?): String {
    val formatter = DateTimeFormatter.ofPattern("EEEE, MMMM d", Locale.ENGLISH)
    return Instant.ofEpochMilli(timeInMillis ?: System.currentTimeMillis()).atZone(ZoneId.systemDefault()).format(formatter)
}
fun formatTimeWithAmPm(hour: Int?, minute: Int?): String {
    val formatHour = hour ?: 7
    val formatMinute = minute ?: 30
    val amPm = if (formatHour < 12) "AM" else "PM"
    val formattedHour = when {
        formatHour == 0 -> 12
        formatHour > 12 -> formatHour - 12
        else -> formatHour
    }
    val formattedMinute = formatMinute.toString().padStart(2, '0')
    return "$formattedHour:$formattedMinute $amPm"
}
