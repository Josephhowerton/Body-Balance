package enums

enum class SystemOfMeasurement{
    METRIC,
    CUSTOMARY
}

enum class TimeUnits {
    SECONDS,
    MINUTES,
    HOUR
}
enum class LengthUnit(val toMeter: Double) {
    METER(1.0),
    KILOMETER(1000.0),
    CENTIMETER(0.01),
    MILLIMETER(0.001),
    INCHES(0.0254),
    FEET(0.3048),
    YARDS(0.9144),
    MILES(1609.34)
}

enum class MassUnit(val toKilogram: Double) {
    GRAM(0.001),
    KILOGRAM(1.0),
    MILLIGRAM(1e-6),
    OUNCES(0.0283495),
    POUNDS(0.453592)
}

enum class VolumeUnit(val toLiter: Double) {
    LITER(1.0),
    MILLILITER(0.001),
    TEASPOON(0.00492892),
    TABLESPOON(0.0147868),
    FLUID_OUNCES(0.0295735),
    CUPS(0.236588),
    PINTS(0.473176),
    QUARTS(0.946353),
    GALLONS(3.78541)
}

enum class TemperatureUnit {
    CELSIUS,
    FAHRENHEIT
}

fun convertLength(value: Double, fromUnit: LengthUnit, toUnit: LengthUnit): Double {
    return value * fromUnit.toMeter / toUnit.toMeter
}

fun convertMass(value: Double, fromUnit: MassUnit, toUnit: MassUnit): Double {
    return value * fromUnit.toKilogram / toUnit.toKilogram
}

fun convertVolume(value: Double, fromUnit: VolumeUnit, toUnit: VolumeUnit): Double {
    return value * fromUnit.toLiter / toUnit.toLiter
}

fun convertTemperature(value: Double, fromUnit: TemperatureUnit, toUnit: TemperatureUnit): Double {
    return when {
        fromUnit == TemperatureUnit.CELSIUS && toUnit == TemperatureUnit.FAHRENHEIT -> value * 1.8 + 32
        fromUnit == TemperatureUnit.FAHRENHEIT && toUnit == TemperatureUnit.CELSIUS -> (value - 32) / 1.8
        else -> value
    }
}

fun formatWeight(value: Double, toUnit: MassUnit): String {
    return when (toUnit) {
        MassUnit.GRAM -> {
            val grams = value * 453.592 // Convert pounds to grams
            "${grams.toInt()} g"
        }
        MassUnit.KILOGRAM -> {
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

fun formatHeight(value: Double, toUnit: LengthUnit): String {
    return when (toUnit) {
        LengthUnit.FEET -> {
            val (feet, inches) = value.toFeetAndInchesFromCm()
            "$feet' $inches\""
        }
        LengthUnit.METER -> {
            val meters = value / 100.0 // Convert centimeters to meters
            "${String.format("%.2f", meters)} m"
        }
        else -> "${value.toInt()} cm" // Centimeters
    }
}
