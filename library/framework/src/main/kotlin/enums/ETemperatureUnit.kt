package enums

import com.fitness.resources.R

enum class ETemperatureUnit(val unitResourceId: Int, systemOfMeasurement: SystemOfMeasurement) {
    CELSIUS(R.string.unit_celsius, SystemOfMeasurement.METRIC),
    FAHRENHEIT(R.string.unit_fahrenheit, SystemOfMeasurement.CUSTOMARY)
}