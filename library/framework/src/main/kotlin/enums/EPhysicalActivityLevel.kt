package enums

import com.fitness.resources.R

enum class EPhysicalActivityLevel(val title: Int, val description: Int) {
    SEDENTARY(R.string.title_fitness_frequency_sedentary, R.string.desc_fitness_frequency_sedentary),
    LIGHTLY_ACTIVE(R.string.title_fitness_frequency_lightly, R.string.desc_fitness_frequency_lightly),
    MODERATELY_ACTIVE(R.string.title_fitness_frequency_moderately_active, R.string.desc_fitness_frequency_moderately_active),
    VERY_ACTIVE(R.string.title_fitness_frequency_very_active, R.string.desc_fitness_frequency_very_active),
    EXTRA_ACTIVE(R.string.title_fitness_frequency_extra_active, R.string.desc_fitness_frequency_extra_active)
}