package enums

import com.fitness.resources.R

enum class ELengthUnit(val toMeter: Double, val unitResourceId: Int, systemOfMeasurement: SystemOfMeasurement){
    METER(1.0, R.string.unit_meter, SystemOfMeasurement.METRIC),
    KILOMETER(1000.0, R.string.unit_kilometer, SystemOfMeasurement.METRIC),
    CENTIMETER(0.01, R.string.unit_centimeter, SystemOfMeasurement.METRIC),
    MILLIMETER(0.001, R.string.unit_millimeter, SystemOfMeasurement.METRIC),
    INCHES(0.0254, R.string.unit_inches, SystemOfMeasurement.CUSTOMARY),
    FEET(0.3048, R.string.unit_feet, SystemOfMeasurement.CUSTOMARY),
    YARDS(0.9144, R.string.unit_yards, SystemOfMeasurement.CUSTOMARY),
    MILES(1609.34, R.string.unit_miles, SystemOfMeasurement.CUSTOMARY)
}