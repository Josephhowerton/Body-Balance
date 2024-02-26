package enums

import com.fitness.resources.R

inline fun <reified T : Enum<T>> safeEnumValueOf(type: String): T? {
    return try {
        enumValueOf<T>(type)
    } catch (e: IllegalArgumentException) {
        null
    }
}

fun convertLength(value: Double, fromUnit: ELengthUnit, toUnit: ELengthUnit): Double {
    return value * fromUnit.toMeter / toUnit.toMeter
}

fun convertMass(value: Double, fromUnit: EMassUnit, toUnit: EMassUnit): Double {
    return value * fromUnit.toKilogram / toUnit.toKilogram
}

fun convertVolume(value: Double, fromUnit: EVolumeUnit, toUnit: EVolumeUnit): Double {
    return value * fromUnit.toLiter / toUnit.toLiter
}

fun convertTemperature(value: Double, fromUnit: ETemperatureUnit, toUnit: ETemperatureUnit): Double {
    return when {
        fromUnit == ETemperatureUnit.CELSIUS && toUnit == ETemperatureUnit.FAHRENHEIT -> value * 1.8 + 32
        fromUnit == ETemperatureUnit.FAHRENHEIT && toUnit == ETemperatureUnit.CELSIUS -> (value - 32) / 1.8
        else -> value
    }
}

fun formatWeight(value: Double, toUnit: EMassUnit): String {
    return when (toUnit) {
        EMassUnit.GRAM -> {
            val grams = value * 453.592 // Convert pounds to grams
            "${grams.toInt()} g"
        }
        EMassUnit.KILOGRAM -> {
            val kilograms = value * 0.453592 // Convert pounds to kilograms
            "${String.format("%.2f", kilograms)} kg"
        }
        else -> "${String.format("%.2f", value)} lbs" // Pounds
    }
}

fun Double.toFeetAndInchesFromCm(): Pair<Int, Int> {
    val totalInches = this / 2.54
    val feet = (totalInches / 12).toInt()
    val remainingInches = (totalInches % 12).toInt()

    return Pair(feet, remainingInches)
}

fun formatHeight(value: Double, toUnit: ELengthUnit): String {
    return when (toUnit) {
        ELengthUnit.FEET -> {
            val (feet, inches) = value.toFeetAndInchesFromCm()
            "$feet' $inches\""
        }

        ELengthUnit.METER -> {
            val meters = value / 100.0 // Convert centimeters to meters
            "${String.format("%.2f", meters)} m"
        }

        else -> "${value.toInt()} cm" // Centimeters
    }
}
fun formatLength(value: Double, unit: ELengthUnit): String {
    return when (unit) {
        ELengthUnit.METER -> "${String.format("%.1f", value)} m"
        ELengthUnit.KILOMETER -> "${String.format("%.1f", value)} km"
        ELengthUnit.CENTIMETER -> "${String.format("%.1f", value)} cm"
        ELengthUnit.MILLIMETER -> "${String.format("%.1f", value)} mm"
        ELengthUnit.INCHES -> "${String.format("%.1f", value)} in"
        ELengthUnit.FEET -> "${String.format("%.1f", value)} ft"
        ELengthUnit.YARDS -> "${String.format("%.1f", value)} yd"
        ELengthUnit.MILES -> "${String.format("%.1f", value)} mi"
    }
}
