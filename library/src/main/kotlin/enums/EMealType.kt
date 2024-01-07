package enums

import com.fitness.resources.R
import extensions.Item

enum class EMealType(override val title: Int, override val desc: Int? = null): Item {
    BREAKFAST(R.string.title_breakfast),
    BRUNCH(R.string.title_brunch),
    LUNCH_DINNER(R.string.title_lunch_dinner),
    SNACK(R.string.title_snack),
    TEATIME(R.string.title_teatime);
}
