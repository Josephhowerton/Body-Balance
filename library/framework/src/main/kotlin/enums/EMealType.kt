package enums

import com.fitness.resources.R
import extensions.GeneralItem
import network.nutrition.RecipeParameters

enum class EMealType(override val title: Int, override val desc: Int? = null, override val apiParameter: String?): GeneralItem {
    BREAKFAST(title = R.string.title_breakfast, apiParameter = RecipeParameters.BREAKFAST),
    BRUNCH(title = R.string.title_brunch, apiParameter = RecipeParameters.BRUNCH),
    LUNCH_DINNER(title = R.string.title_lunch_dinner, apiParameter = RecipeParameters.LUNCH_DINNER),
    SNACK(title = R.string.title_snack, apiParameter = RecipeParameters.SNACK),
    TEATIME(title = R.string.title_teatime, apiParameter = RecipeParameters.TEATIME)
}