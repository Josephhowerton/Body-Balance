package enums

import com.fitness.resources.R
import extensions.Item
import network.nutrition.RecipeParameters

enum class EHealthLabel(override val title: Int, override val desc: Int?, override val apiParameter: String?): Item {
    ALCOHOL_COCKTAIL(title = R.string.title_alcohol_cocktail, desc = R.string.def_alcohol_cocktail, apiParameter = RecipeParameters.ALCOHOL_COCKTAIL),
    ALCOHOL_FREE(title = R.string.title_alcohol_free, desc = R.string.def_alcohol_free, apiParameter = RecipeParameters.ALCOHOL_FREE),
    CELERY_FREE(title = R.string.title_celery_free, desc = R.string.def_celery_free, apiParameter = RecipeParameters.CELERY_FREE),
    CRUSTACEAN_FREE(title = R.string.title_crustacean_free, desc = R.string.def_crustacean_free, apiParameter = RecipeParameters.CRUSTACEAN_FREE),
    DAIRY_FREE(title = R.string.title_dairy_free, desc = R.string.def_dairy_free, apiParameter = RecipeParameters.DAIRY_FREE),
    DASH(title = R.string.title_dash, desc = R.string.def_dash, apiParameter = RecipeParameters.DASH),
    EGG_FREE(title = R.string.title_egg_free, desc = R.string.def_egg_free, apiParameter = RecipeParameters.EGG_FREE),
    FISH_FREE(title = R.string.title_fish_free, desc = R.string.def_fish_free, apiParameter = RecipeParameters.FISH_FREE),
    FODMAP_FREE(title = R.string.title_fodmap_free, desc = R.string.def_fodmap_free, apiParameter = RecipeParameters.FODMAP_FREE),
    GLUTEN_FREE(title = R.string.title_gluten_free, desc = R.string.def_gluten_free, apiParameter = RecipeParameters.GLUTEN_FREE),
    IMMUNO_SUPPORTIVE(title = R.string.title_immuno_supportive, desc = R.string.def_immuno_supportive, apiParameter = RecipeParameters.IMMUNO_SUPPORTIVE),
    KETO_FRIENDLY(title = R.string.title_keto_friendly, desc = R.string.def_keto_friendly, apiParameter = RecipeParameters.KETO_FRIENDLY),
    KIDNEY_FRIENDLY(title = R.string.title_kidney_friendly, desc = R.string.def_kidney_friendly, apiParameter = RecipeParameters.KIDNEY_FRIENDLY),
    KOSHER(title = R.string.title_kosher, desc = R.string.def_kosher, apiParameter = RecipeParameters.KOSHER),
    LOW_POTASSIUM(title = R.string.title_low_potassium, desc = R.string.def_low_potassium, apiParameter = RecipeParameters.LOW_POTASSIUM),
    LOW_SUGAR(title = R.string.title_low_sugar, desc = R.string.def_low_sugar, apiParameter = RecipeParameters.LOW_SUGAR),
    LUPINE_FREE(title = R.string.title_lupine_free, desc = R.string.def_lupine_free, apiParameter = RecipeParameters.LUPINE_FREE),
    MEDITERRANEAN(title = R.string.title_mediterranean, desc = R.string.def_mediterranean, apiParameter = RecipeParameters.MEDITERRANEAN),
    MOLLUSK_FREE(title = R.string.title_mollusk_free, desc = R.string.def_mollusk_free, apiParameter = RecipeParameters.MOLLUSK_FREE),
    MUSTARD_FREE(title = R.string.title_mustard_free, desc = R.string.def_mustard_free, apiParameter = RecipeParameters.MUSTARD_FREE),
    NO_OIL_ADDED(title = R.string.title_no_oil_added, desc = R.string.def_no_oil_added, apiParameter = RecipeParameters.NO_OIL_ADDED),
    PALEO(title = R.string.title_paleo, desc = R.string.def_paleo, apiParameter = RecipeParameters.PALEO),
    PEANUT_FREE(title = R.string.title_peanut_free, desc = R.string.def_peanut_free, apiParameter = RecipeParameters.PEANUT_FREE),
    PESCATARIAN(title = R.string.title_pescatarian, desc = R.string.def_pescatarian, apiParameter = RecipeParameters.PESCATARIAN),
    PORK_FREE(title = R.string.title_pork_free, desc = R.string.def_pork_free, apiParameter = RecipeParameters.PORK_FREE),
    RED_MEAT_FREE(title = R.string.title_red_meat_free, desc = R.string.def_red_meat_free, apiParameter = RecipeParameters.RED_MEAT_FREE),
    SESAME_FREE(title = R.string.title_sesame_free, desc = R.string.def_sesame_free, apiParameter = RecipeParameters.SESAME_FREE),
    SHELLFISH_FREE(title = R.string.title_shellfish_free, desc = R.string.def_shellfish_free, apiParameter = RecipeParameters.SHELLFISH_FREE),
    SOY_FREE(title = R.string.title_soy_free, desc = R.string.def_soy_free, apiParameter = RecipeParameters.SOY_FREE),
    SUGAR_CONSCIOUS(title = R.string.title_sugar_conscious, desc = R.string.def_sugar_conscious, apiParameter = RecipeParameters.SUGAR_CONSCIOUS),
    SULFITE_FREE(title = R.string.title_sulfite_free, desc = R.string.def_sulfite_free, apiParameter = RecipeParameters.SULFITE_FREE),
    TREE_NUT_FREE(title = R.string.title_tree_nut_free, desc = R.string.def_tree_nut_free, apiParameter = RecipeParameters.TREE_NUT_FREE),
    VEGAN(title = R.string.title_vegan, desc = R.string.def_vegan, apiParameter = RecipeParameters.VEGAN),
    VEGETARIAN(title = R.string.title_vegetarian, desc = R.string.def_vegetarian, apiParameter = RecipeParameters.VEGETARIAN),
    WHEAT_FREE(title = R.string.title_wheat_free, desc = R.string.def_wheat_free, apiParameter = RecipeParameters.WHEAT_FREE)
}

