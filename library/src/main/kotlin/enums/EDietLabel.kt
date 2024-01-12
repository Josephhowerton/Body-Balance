package enums

import com.fitness.resources.R
import extensions.Item
import network.nutrition.RecipeParameters

enum class EDietLabel(override val title: Int, override val desc: Int?, override val apiParameter: String?): Item {
    BALANCED(title = R.string.balanced_title, desc = R.string.balanced_description, apiParameter = RecipeParameters.BALANCED),
    HIGH_FIBER(title = R.string.high_fiber_title, desc = R.string.high_fiber_description, apiParameter = RecipeParameters.HIGH_FIBER),
    HIGH_PROTEIN(title = R.string.high_protein_title, desc = R.string.high_protein_description, apiParameter = RecipeParameters.HIGH_PROTEIN),
    LOW_CARB(title = R.string.low_carb_title, desc = R.string.low_carb_description, apiParameter = RecipeParameters.LOW_CARB),
    LOW_FAT(title = R.string.low_fat_title, desc = R.string.low_fat_description, apiParameter = RecipeParameters.LOW_FAT),
    LOW_SODIUM(title = R.string.low_sodium_title, desc = R.string.low_sodium_description, apiParameter = RecipeParameters.LOW_SODIUM)
}
