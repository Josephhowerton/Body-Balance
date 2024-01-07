package enums

import com.fitness.resources.R
import extensions.Item

enum class EHealthLabel(override val title: Int, override val desc: Int?): Item {
    ALCOHOL_COCKTAIL(R.string.title_alcohol_cocktail, R.string.def_alcohol_cocktail),
    ALCOHOL_FREE(R.string.title_alcohol_free, R.string.def_alcohol_free),
    CELERY_FREE(R.string.title_celery_free, R.string.def_celery_free),
    CRUSTACEAN_FREE(R.string.title_crustacean_free, R.string.def_crustacean_free),
    DAIRY_FREE(R.string.title_dairy_free, R.string.def_dairy_free),
    DASH(R.string.title_dash, R.string.def_dash),
    EGG_FREE(R.string.title_egg_free, R.string.def_egg_free),
    FISH_FREE(R.string.title_fish_free, R.string.def_fish_free),
    FODMAP_FREE(R.string.title_fodmap_free, R.string.def_fodmap_free),
    GLUTEN_FREE(R.string.title_gluten_free, R.string.def_gluten_free),
    IMMUNO_SUPPORTIVE(R.string.title_immuno_supportive, R.string.def_immuno_supportive),
    KETO_FRIENDLY(R.string.title_keto_friendly, R.string.def_keto_friendly),
    KIDNEY_FRIENDLY(R.string.title_kidney_friendly, R.string.def_kidney_friendly),
    KOSHER(R.string.title_kosher, R.string.def_kosher),
    LOW_POTASSIUM(R.string.title_low_potassium, R.string.def_low_potassium),
    LOW_SUGAR(R.string.title_low_sugar, R.string.def_low_sugar),
    LUPINE_FREE(R.string.title_lupine_free, R.string.def_lupine_free),
    MEDITERRANEAN(R.string.title_mediterranean, R.string.def_mediterranean),
    MOLLUSK_FREE(R.string.title_mollusk_free, R.string.def_mollusk_free),
    MUSTARD_FREE(R.string.title_mustard_free, R.string.def_mustard_free),
    NO_OIL_ADDED(R.string.title_no_oil_added, R.string.def_no_oil_added),
    PALEO(R.string.title_paleo, R.string.def_paleo),
    PEANUT_FREE(R.string.title_peanut_free, R.string.def_peanut_free),
    PESCATARIAN(R.string.title_pescatarian, R.string.def_pescatarian),
    PORK_FREE(R.string.title_pork_free, R.string.def_pork_free),
    RED_MEAT_FREE(R.string.title_red_meat_free, R.string.def_red_meat_free),
    SESAME_FREE(R.string.title_sesame_free, R.string.def_sesame_free),
    SHELLFISH_FREE(R.string.title_shellfish_free, R.string.def_shellfish_free),
    SOY_FREE(R.string.title_soy_free, R.string.def_soy_free),
    SUGAR_CONSCIOUS(R.string.title_sugar_conscious, R.string.def_sugar_conscious),
    SULFITE_FREE(R.string.title_sulfite_free, R.string.def_sulfite_free),
    TREE_NUT_FREE(R.string.title_tree_nut_free, R.string.def_tree_nut_free),
    VEGAN(R.string.title_vegan, R.string.def_vegan),
    VEGETARIAN(R.string.title_vegetarian, R.string.def_vegetarian),
    WHEAT_FREE(R.string.title_wheat_free, R.string.def_wheat_free);
}

