package enums

import com.fitness.resources.R
enum class EMealType(val titleResId: Int) {
    BREAKFAST(R.string.title_breakfast),
    BRUNCH(R.string.title_brunch),
    LUNCH_DINNER(R.string.title_lunch_dinner),
    SNACK(R.string.title_snack),
    TEATIME(R.string.title_teatime);
}
