package enums

import com.fitness.resources.R

enum class EMassUnit(val toKilogram: Double, val unitResourceId: Int, systemOfMeasurement: SystemOfMeasurement): Units {
    GRAM(0.001, R.string.unit_gram, SystemOfMeasurement.METRIC),
    KILOGRAM(1.0, R.string.unit_kilogram, SystemOfMeasurement.METRIC),
    MILLIGRAM(1e-6, R.string.unit_milligram, SystemOfMeasurement.METRIC),
    MICROGRAM(1e-9, R.string.unit_microgram, SystemOfMeasurement.METRIC),
    OUNCES(0.0283495, R.string.unit_ounces, SystemOfMeasurement.CUSTOMARY),
    POUNDS(0.453592, R.string.unit_pounds, SystemOfMeasurement.CUSTOMARY)
}