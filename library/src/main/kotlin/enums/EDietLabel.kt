package enums

import com.fitness.resources.R

enum class EDietLabel(val titleResId: Int, val descriptionResId: Int) {
    BALANCED(R.string.balanced_title, R.string.balanced_description),
    HIGH_FIBER(R.string.high_fiber_title, R.string.high_fiber_description),
    HIGH_PROTEIN(R.string.high_protein_title, R.string.high_protein_description),
    LOW_CARB(R.string.low_carb_title, R.string.low_carb_description),
    LOW_FAT(R.string.low_fat_title, R.string.low_fat_description),
    LOW_SODIUM(R.string.low_sodium_title, R.string.low_sodium_description)
}
