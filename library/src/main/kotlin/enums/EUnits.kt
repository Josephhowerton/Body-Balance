package enums

import com.fitness.resources.R

interface Units

enum class SystemOfMeasurement {
    METRIC,
    CUSTOMARY
}

enum class TimeUnits {
    SECONDS,
    MINUTES,
    HOUR
}

enum class LengthUnit(val toMeter: Double, val unitResourceId: Int): Units {
    METER(1.0, R.string.unit_meter),
    KILOMETER(1000.0, R.string.unit_kilometer),
    CENTIMETER(0.01, R.string.unit_centimeter),
    MILLIMETER(0.001, R.string.unit_millimeter),
    INCHES(0.0254, R.string.unit_inches),
    FEET(0.3048, R.string.unit_feet),
    YARDS(0.9144, R.string.unit_yards),
    MILES(1609.34, R.string.unit_miles)
}

enum class MassUnit(val toKilogram: Double, val unitResourceId: Int): Units {
    GRAM(0.001, R.string.unit_gram),
    KILOGRAM(1.0, R.string.unit_kilogram),
    MILLIGRAM(1e-6, R.string.unit_milligram),
    MICROGRAM(1e-9, R.string.unit_microgram),
    OUNCES(0.0283495, R.string.unit_ounces),
    POUNDS(0.453592, R.string.unit_pounds)
}

enum class VolumeUnit(val toLiter: Double, val unitResourceId: Int): Units {
    LITER(1.0, R.string.unit_liter),
    MILLILITER(0.001, R.string.unit_milliliter),
    TEASPOON(0.00492892, R.string.unit_teaspoon),
    TABLESPOON(0.0147868, R.string.unit_tablespoon),
    FLUID_OUNCES(0.0295735, R.string.unit_fluid_ounces),
    CUPS(0.236588, R.string.unit_cups),
    PINTS(0.473176, R.string.unit_pints),
    QUARTS(0.946353, R.string.unit_quarts),
    GALLONS(3.78541, R.string.unit_gallons)
}

enum class TemperatureUnit(val unitResourceId: Int) {
    CELSIUS(R.string.unit_celsius),
    FAHRENHEIT(R.string.unit_fahrenheit)
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
