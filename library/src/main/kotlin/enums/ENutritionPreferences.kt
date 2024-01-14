package enums

import com.fitness.resources.R
import extensions.GeneralItem

enum class ENutritionPreferences(override val title: Int, override val desc: Int? = null, override val apiParameter: String? = null): GeneralItem {
    BALANCED_DIET(R.string.title_balanced_diet),
    VEGAN_DIET(R.string.title_vegan),
    VEGETARIAN_DIET(R.string.title_vegetarian),
    KETO_DIET(R.string.title_keto),
    PALEO_DIET(R.string.title_paleo),
    PESCETARIAN_DIET(R.string.title_pescetarian),
    MEDITERRANEAN_DIET(R.string.title_mediterranean),
    WHOLE30_DIET(R.string.title_whole30),
    LOW_CARB_DIET(R.string.title_low_carb),
    HIGH_PROTEIN_DIET(R.string.title_high_protein),
    INTERMITTENT_FASTING(R.string.title_intermittent_fasting),
    PLANT_BASED_DIET(R.string.title_plant_based),
    GLUTEN_FREE_DIET(R.string.title_gluten_free),
    DAIRY_FREE_DIET(R.string.title_dairy_free),
    DETOX_DIET(R.string.title_detox),
    HYDRATION(R.string.title_hydration),
    WEIGHT_CONTROL(R.string.title_weight_control),
    PORTION_SIZE(R.string.title_portion_size),
    LESS_SCREEN(R.string.title_less_screen),
    WHOLE_FOODS(R.string.title_whole_foods),
    HEALTHY_SNACKS(R.string.title_healthy_snacks),
    LESS_SUGAR(R.string.title_less_sugar),
    LESS_CAFFEINE(R.string.title_less_caffeine),
    NO_SMOKING(R.string.title_no_smoking),
    MODERATE_ALCOHOL(R.string.title_moderate_alcohol);
}

