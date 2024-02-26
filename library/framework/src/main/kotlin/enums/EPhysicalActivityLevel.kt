package enums

import com.fitness.resources.R

enum class EPhysicalActivityLevel(val title: Int, val description: Int, val factor: Double) {
    SEDENTARY(R.string.title_fitness_frequency_sedentary, R.string.desc_fitness_frequency_sedentary, 1.2),
    LIGHTLY_ACTIVE(R.string.title_fitness_frequency_lightly, R.string.desc_fitness_frequency_lightly, 1.375),
    MODERATELY_ACTIVE(R.string.title_fitness_frequency_moderately_active, R.string.desc_fitness_frequency_moderately_active, 1.55),
    VERY_ACTIVE(R.string.title_fitness_frequency_very_active, R.string.desc_fitness_frequency_very_active, 1.725),
    EXTRA_ACTIVE(R.string.title_fitness_frequency_extra_active, R.string.desc_fitness_frequency_extra_active, 1.9)
}