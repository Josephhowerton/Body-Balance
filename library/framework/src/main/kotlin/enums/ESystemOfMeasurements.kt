package enums

import com.fitness.resources.R

enum class SystemOfMeasurement(val title: Int, val description: Int) {
    METRIC(R.string.metric_system_title, R.string.metric_system_description),
    CUSTOMARY(R.string.customary_system_title, R.string.customary_system_description)
}
