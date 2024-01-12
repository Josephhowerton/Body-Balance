package enums

import com.fitness.resources.R
import extensions.Item
import network.nutrition.RecipeParameters

enum class EDishType(override val title: Int, override val desc: Int? = null, override val apiParameter: String?): Item {
    ALCOHOL_COCKTAIL(title = R.string.title_alcohol_cocktail, apiParameter = RecipeParameters.ALCOHOL_COCKTAIL),
    BISCUITS_AND_COOKIES(title = R.string.title_biscuits_and_cookies, apiParameter = RecipeParameters.BISCUITS_AND_COOKIES),
    BREAD(title = R.string.title_bread, apiParameter = RecipeParameters.BREAD),
    CEREALS(title = R.string.title_cereals, apiParameter = RecipeParameters.CEREALS),
    CONDIMENTS_AND_SAUCES(title = R.string.title_condiments_and_sauces, apiParameter = RecipeParameters.CONDIMENTS_AND_SAUCES),
    DESSERTS(title = R.string.title_desserts, apiParameter = RecipeParameters.DESSERTS),
    DRINKS(title = R.string.title_drinks, apiParameter = RecipeParameters.DRINKS),
    EGG(title = R.string.title_egg, apiParameter = RecipeParameters.EGG),
    ICE_CREAM_AND_CUSTARD(title = R.string.title_ice_cream_and_custard, apiParameter = RecipeParameters.ICE_CREAM_AND_CUSTARD),
    MAIN_COURSE(title = R.string.title_main_course, apiParameter = RecipeParameters.MAIN_COURSE),
    PANCAKE(title = R.string.title_pancake, apiParameter = RecipeParameters.PANCAKE),
    PASTA(title = R.string.title_pasta, apiParameter = RecipeParameters.PASTA),
    PASTRY(title = R.string.title_pastry, apiParameter = RecipeParameters.PASTRY),
    PIES_AND_TARTS(title = R.string.title_pies_and_tarts, apiParameter = RecipeParameters.PIES_AND_TARTS),
    PIZZA(title = R.string.title_pizza, apiParameter = RecipeParameters.PIZZA),
    PREPS(title = R.string.title_preps, apiParameter = RecipeParameters.PREPS),
    PRESERVE(title = R.string.title_preserve, apiParameter = RecipeParameters.PRESERVE),
    SALAD(title = R.string.title_salad, apiParameter = RecipeParameters.SALAD),
    SANDWICHES(title = R.string.title_sandwiches, apiParameter = RecipeParameters.SANDWICHES),
    SEAFOOD(title = R.string.title_seafood, apiParameter = RecipeParameters.SEAFOOD),
    SIDE_DISH(title = R.string.title_side_dish, apiParameter = RecipeParameters.SIDE_DISH),
    SOUP(title = R.string.title_soup, apiParameter = RecipeParameters.SOUP),
    SPECIAL_OCCASIONS(title = R.string.title_special_occasions, apiParameter = RecipeParameters.SPECIAL_OCCASIONS),
    STARTER(title = R.string.title_starter, apiParameter = RecipeParameters.STARTER),
    SWEETS(title = R.string.title_sweets, apiParameter = RecipeParameters.SWEETS),
    OMELET(title = R.string.title_omelet, apiParameter = RecipeParameters.OMELET),
}
