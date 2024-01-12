package enums

import com.fitness.resources.R
import extensions.Item

enum class EDietaryRestrictions(override val title: Int, override val desc: Int? = null, override val apiParameter: String? = null): Item {
    NO_DAIRY(R.string.title_no_dairy),
    NO_GLUTEN(R.string.title_no_gluten),
    NO_NUTS(R.string.title_no_nuts),
    NO_SOY(R.string.title_no_soy),
    NO_EGGS(R.string.title_no_eggs),
    NO_SHELLFISH(R.string.title_no_shellfish),
    NO_FISH(R.string.title_no_fish),
    LOW_SODIUM(R.string.title_low_sodium),
    LOW_SUGAR(R.string.title_low_sugar),
    DIABETIC_FRIENDLY(R.string.title_diabetic_friendly),
    HALAL(R.string.title_halal),
    KOSHER(R.string.title_kosher),
    VEGAN(R.string.title_vegan),
    VEGETARIAN(R.string.title_vegetarian),
    PESCETARIAN(R.string.title_pescetarian),
    LACTOSE_FREE(R.string.title_lactose_free),
    FODMAP(R.string.title_fodmap),
    PALEO(R.string.title_paleo),
    KETO(R.string.title_keto),
    WHOLE30(R.string.title_whole30),
    NIGHTSHADE_FREE(R.string.title_nightshade_free),
    LOW_FODMAP(R.string.title_low_fodmap),
    AIP(R.string.title_aip), // Autoimmune Protocol
    CORN_FREE(R.string.title_corn_free),
    SUGAR_FREE(R.string.title_sugar_free);
}
