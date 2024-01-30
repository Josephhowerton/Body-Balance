package enums

import com.fitness.resources.R
enum class EVolumeUnit(val toLiter: Double, val unitResourceId: Int, systemOfMeasurement: SystemOfMeasurement): Units {
    LITER(1.0, R.string.unit_liter, SystemOfMeasurement.METRIC),
    MILLILITER(0.001, R.string.unit_milliliter, SystemOfMeasurement.METRIC),
    TEASPOON(0.00492892, R.string.unit_teaspoon, SystemOfMeasurement.CUSTOMARY),
    TABLESPOON(0.0147868, R.string.unit_tablespoon, SystemOfMeasurement.CUSTOMARY),
    FLUID_OUNCES(0.0295735, R.string.unit_fluid_ounces, SystemOfMeasurement.CUSTOMARY),
    CUPS(0.236588, R.string.unit_cups, SystemOfMeasurement.CUSTOMARY),
    PINTS(0.473176, R.string.unit_pints, SystemOfMeasurement.CUSTOMARY),
    QUARTS(0.946353, R.string.unit_quarts, SystemOfMeasurement.CUSTOMARY),
    GALLONS(3.78541, R.string.unit_gallons, SystemOfMeasurement.CUSTOMARY)
}
