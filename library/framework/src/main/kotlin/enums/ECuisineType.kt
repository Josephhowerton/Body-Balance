package enums

import com.fitness.resources.R
import extensions.GeneralItem
import network.nutrition.RecipeParameters

enum class ECuisineType(override val title: Int, override val desc: Int? = null, override val apiParameter: String?): GeneralItem {
    AMERICAN(title = R.string.title_american, apiParameter = RecipeParameters.AMERICAN),
    ASIAN(title = R.string.title_asian, apiParameter =  RecipeParameters.ASIAN),
    BRITISH(title = R.string.title_british, apiParameter =  RecipeParameters.BRITISH),
    CARIBBEAN(title = R.string.title_caribbean, apiParameter =  RecipeParameters.CARIBBEAN),
    CENTRAL_EUROPE(title = R.string.title_central_europe, apiParameter =  RecipeParameters.CENTRAL_EUROPE),
    CHINESE(title = R.string.title_chinese, apiParameter =  RecipeParameters.CHINESE),
    EASTERN_EUROPE(title = R.string.title_eastern_europe, apiParameter =  RecipeParameters.EASTERN_EUROPE),
    FRENCH(title = R.string.title_french, apiParameter =  RecipeParameters.FRENCH),
    GREEK(title = R.string.title_greek, apiParameter =  RecipeParameters.GREEK),
    INDIAN(title = R.string.title_indian, apiParameter =  RecipeParameters.INDIAN),
    ITALIAN(title = R.string.title_italian, apiParameter =  RecipeParameters.ITALIAN),
    JAPANESE(title = R.string.title_japanese, apiParameter =  RecipeParameters.JAPANESE),
    KOREAN(title = R.string.title_korean, apiParameter =  RecipeParameters.KOREAN),
    KOSHER(title = R.string.title_kosher, apiParameter =  RecipeParameters.KOSHER),
    MEDITERRANEAN(title = R.string.title_mediterranean, apiParameter =  RecipeParameters.MEDITERRANEAN),
    MEXICAN(title = R.string.title_mexican, apiParameter =  RecipeParameters.MEXICAN),
    MIDDLE_EASTERN(title = R.string.title_middle_eastern, apiParameter =  RecipeParameters.MIDDLE_EASTERN),
    NORDIC(title = R.string.title_nordic, apiParameter =  RecipeParameters.NORDIC),
    SOUTH_AMERICAN(title = R.string.title_south_american, apiParameter =  RecipeParameters.SOUTH_AMERICAN),
    SOUTH_EAST_ASIAN(title = R.string.title_south_east_asian, apiParameter =  RecipeParameters.SOUTH_EAST_ASIAN),
    WORLD(title = R.string.title_world, apiParameter =  RecipeParameters.WORLD),
}
