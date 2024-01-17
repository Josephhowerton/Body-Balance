package com.fitness.domain.model.nutrition

import com.fitness.data.model.cache.nutrition.IngredientEntity
import com.fitness.data.model.cache.nutrition.NutrientEntity
import com.fitness.data.model.cache.nutrition.NutritionEntity
import com.fitness.data.model.cache.nutrition.RecipeEntity
import com.fitness.data.model.network.edamam.recipe.IngredientDto
import com.fitness.data.model.network.edamam.recipe.RecipeDto
import com.fitness.data.model.network.edamam.shared.TotalNutrientsDto
import com.fitness.data.util.TotalNutrientsKeys

fun Nutrition.toNutritionEntity(): NutritionEntity =
    NutritionEntity(
        recordId = recordId,
        userId = userId,
        dateTime = dateTime,
        mealType = mealType,
        recipe = recipe.toRecipeEntity()
    )

fun NutritionEntity.toNutrition(): Nutrition =
    Nutrition(
        recordId = recordId,
        userId = userId,
        dateTime = dateTime,
        mealType = mealType,
        recipe = recipe.toRecipe()
    )

fun Recipe.toRecipeEntity(): RecipeEntity =
    RecipeEntity(
        recipeId = recipeId,
        calories = calories,
        cautions = cautions,
        cuisineType = cuisineType,
        dietLabels = dietLabels,
        dishType = dishType,
        healthLabels = healthLabels,
        standardImage = standardImage,
        large = large,
        regular = regular,
        small = small,
        thumbnail = thumbnail,
        ingredientLines = ingredientLines,
        instructionLines = instructionLines,
        label = label,
        mealType = mealType,
        shareAs = shareAs,
        source = source,
        tags = tags,
        totalTime = totalTime,
        totalWeight = totalWeight,
        recipeUri = recipeUri,
        recipeUrl = recipeUrl,
        yield = yield,
        ingredients = ingredients?.map { it.toIngredientEntity() },
        nutrients = nutrients.toNutrientEntityMap()
    )

fun RecipeEntity.toRecipe(): Recipe =
    Recipe(
        recipeId = recipeId,
        calories = calories,
        cautions = cautions,
        cuisineType = cuisineType,
        dietLabels = dietLabels,
        dishType = dishType,
        healthLabels = healthLabels,
        standardImage = standardImage,
        large = large,
        regular = regular,
        small = small,
        thumbnail = thumbnail,
        ingredientLines = ingredientLines,
        instructionLines = instructionLines,
        label = label,
        mealType = mealType,
        shareAs = shareAs,
        source = source,
        tags = tags,
        totalTime = totalTime,
        totalWeight = totalWeight,
        recipeUri = recipeUri,
        recipeUrl = recipeUrl,
        yield = yield,
        ingredients = ingredients?.map { it.toIngredient() },
        nutrients = nutrients.toNutrientsMap()
    )

fun RecipeDto.toRecipeFromRecipeDto(): Recipe = Recipe(
    calories = calories,
    cautions = cautions,
    cuisineType = cuisineType,
    dietLabels = dietLabels,
    dishType = dishType,
    healthLabels = healthLabels,
    standardImage = image,
    large = images?.large?.url,
    regular = images?.regular?.url,
    small = images?.small?.url,
    thumbnail = images?.thumbnail?.url,
    ingredientLines = ingredientLines,
    label = label,
    mealType = mealType,
    shareAs = shareAs,
    source = source,
    tags = tags,
    totalTime = totalTime,
    totalWeight = totalWeight,
    recipeUri = uri,
    recipeUrl = url,
    yield = yield,
    ingredients = ingredients?.map { it.toIngredientFromIngredientDto() },
    nutrients = totalNutrients?.toNutrientFromTotalNutrientsDto()
)


fun Ingredient.toIngredientEntity(): IngredientEntity =
    IngredientEntity(
        foodId = foodId,
        food = food,
        foodCategory = foodCategory,
        image = image,
        measure = measure,
        quantity = quantity,
        detailed = detailed,
        weight = weight,
        uri = uri,
        label = label,
        knownAs = knownAs,
        category = category,
        categoryLabel = categoryLabel,
        calories = calories,
        cautions = cautions,
        dietLabels = dietLabels,
        healthLabels = healthLabels,
        totalWeight = totalWeight,
        measureLabel = measureLabel,
        qualifierLabel = qualifierLabel,
        qualifierUri = qualifierUri,
        measureUri = measureUri,
        measureWeight = measureWeight,
        nutrients = nutrients.toNutrientEntityMap()
    )

fun IngredientEntity.toIngredient(): Ingredient =
    Ingredient(
        foodId = foodId,
        food = food,
        foodCategory = foodCategory,
        image = image,
        measure = measure,
        quantity = quantity,
        detailed = detailed,
        weight = weight,
        uri = uri,
        label = label,
        knownAs = knownAs,
        category = category,
        categoryLabel = categoryLabel,
        calories = calories,
        cautions = cautions,
        dietLabels = dietLabels,
        healthLabels = healthLabels,
        totalWeight = totalWeight,
        measureLabel = measureLabel,
        qualifierLabel = qualifierLabel,
        qualifierUri = qualifierUri,
        measureUri = measureUri,
        measureWeight = measureWeight,
        nutrients = nutrients.toNutrientsMap()
    )

fun IngredientDto.toIngredientFromIngredientDto(): Ingredient =
    Ingredient(
        foodId = foodId,
        food = food,
        foodCategory = foodCategory,
        image = image,
        measure = measure,
        quantity = quantity,
        detailed = text,
        weight = weight,
    )

fun Map<String, Nutrient>?.toNutrientEntityMap(): Map<String, NutrientEntity>? {
    return this?.mapValues { it.value.toNutrientEntity() }
}

fun Map<String, NutrientEntity>?.toNutrientsMap(): Map<String, Nutrient>? {
    return this?.mapValues { it.value.toNutrient() }
}

fun Nutrient.toNutrientEntity(): NutrientEntity =
    NutrientEntity(
        label = label,
        quantity = quantity,
        unit = unit
    )

fun NutrientEntity.toNutrient(): Nutrient =
    Nutrient(
        label = label,
        quantity = quantity,
        unit = unit
    )

fun TotalNutrientsDto.toNutrientFromTotalNutrientsDto(): Map<String, Nutrient> =  mapOf(
    TotalNutrientsKeys.KEY_CALCIUM to Nutrient(ca?.label ?: "", ca?.quantity ?: 0.0, ca?.unit ?: ""),
    TotalNutrientsKeys.KEY_CARBOHYDRATES to Nutrient(chocdf?.label ?: "", chocdf?.quantity ?: 0.0, chocdf?.unit ?: ""),
    TotalNutrientsKeys.KEY_NET_CARBS to Nutrient(chocdfnet?.label ?: "", chocdfnet?.quantity ?: 0.0, chocdfnet?.unit ?: ""),
    TotalNutrientsKeys.KEY_CHOLESTEROL to Nutrient(chole?.label ?: "", chole?.quantity ?: 0.0, chole?.unit ?: ""),
    TotalNutrientsKeys.KEY_ENERGY to Nutrient(enerckcal?.label ?: "", enerckcal?.quantity ?: 0.0, enerckcal?.unit ?: ""),
    TotalNutrientsKeys.KEY_MONOUNSATURATED_FATS to Nutrient(fams?.label ?: "", fams?.quantity ?: 0.0, fams?.unit ?: ""),
    TotalNutrientsKeys.KEY_POLYUNSATURATED_FATS to Nutrient(fapu?.label ?: "", fapu?.quantity ?: 0.0, fapu?.unit ?: ""),
    TotalNutrientsKeys.KEY_SATURATED_FATS to Nutrient(fasat?.label ?: "", fasat?.quantity ?: 0.0, fasat?.unit ?: ""),
    TotalNutrientsKeys.KEY_TOTAL_FAT to Nutrient(fat?.label ?: "", fat?.quantity ?: 0.0, fat?.unit ?: ""),
    TotalNutrientsKeys.KEY_TRANS_FAT to Nutrient(fatrn?.label ?: "", fatrn?.quantity ?: 0.0, fatrn?.unit ?: ""),
    TotalNutrientsKeys.KEY_IRON to Nutrient(fe?.label ?: "", fe?.quantity ?: 0.0, fe?.unit ?: ""),
    TotalNutrientsKeys.KEY_FIBER to Nutrient(fibtg?.label ?: "", fibtg?.quantity ?: 0.0, fibtg?.unit ?: ""),
    TotalNutrientsKeys.KEY_FOLIC_ACID to Nutrient(folac?.label ?: "", folac?.quantity ?: 0.0, folac?.unit ?: ""),
    TotalNutrientsKeys.KEY_FOLATE_DFE to Nutrient(foldfe?.label ?: "", foldfe?.quantity ?: 0.0, foldfe?.unit ?: ""),
    TotalNutrientsKeys.KEY_FOOD_FOLATE to Nutrient(folfd?.label ?: "", folfd?.quantity ?: 0.0, folfd?.unit ?: ""),
    TotalNutrientsKeys.KEY_POTASSIUM to Nutrient(k?.label ?: "", k?.quantity ?: 0.0, k?.unit ?: ""),
    TotalNutrientsKeys.KEY_MAGNESIUM to Nutrient(mg?.label ?: "", mg?.quantity ?: 0.0, mg?.unit ?: ""),
    TotalNutrientsKeys.KEY_SODIUM to Nutrient(na?.label ?: "", na?.quantity ?: 0.0, na?.unit ?: ""),
    TotalNutrientsKeys.KEY_NIACIN to Nutrient(nia?.label ?: "", nia?.quantity ?: 0.0, nia?.unit ?: ""),
    TotalNutrientsKeys.KEY_PHOSPHORUS to Nutrient(p?.label ?: "", p?.quantity ?: 0.0, p?.unit ?: ""),
    TotalNutrientsKeys.KEY_PROTEIN to Nutrient(procnt?.label ?: "", procnt?.quantity ?: 0.0, procnt?.unit ?: ""),
    TotalNutrientsKeys.KEY_RIBOFLAVIN to Nutrient(ribf?.label ?: "", ribf?.quantity ?: 0.0, ribf?.unit ?: ""),
    TotalNutrientsKeys.KEY_SUGARS to Nutrient(sugar?.label ?: "", sugar?.quantity ?: 0.0, sugar?.unit ?: ""),
    TotalNutrientsKeys.KEY_ADDED_SUGARS to Nutrient(sugaradded?.label ?: "", sugaradded?.quantity ?: 0.0, sugaradded?.unit ?: ""),
    TotalNutrientsKeys.KEY_THIAMIN to Nutrient(thia?.label ?: "", thia?.quantity ?: 0.0, thia?.unit ?: ""),
    TotalNutrientsKeys.KEY_VITAMIN_E to Nutrient(tocpaha?.label ?: "", tocpaha?.quantity ?: 0.0, tocpaha?.unit ?: ""),
    TotalNutrientsKeys.KEY_VITAMIN_A to Nutrient(vitarAE?.label ?: "", vitarAE?.quantity ?: 0.0, vitarAE?.unit ?: ""),
    TotalNutrientsKeys.KEY_VITAMIN_B12 to Nutrient(vitb12?.label ?: "", vitb12?.quantity ?: 0.0, vitb12?.unit ?: ""),
    TotalNutrientsKeys.KEY_VITAMIN_B6 to Nutrient(vitb6a?.label ?: "", vitb6a?.quantity ?: 0.0, vitb6a?.unit ?: ""),
    TotalNutrientsKeys.KEY_VITAMIN_C to Nutrient(vitc?.label ?: "", vitc?.quantity ?: 0.0, vitc?.unit ?: ""),
    TotalNutrientsKeys.KEY_VITAMIN_D to Nutrient(vitd?.label ?: "", vitd?.quantity ?: 0.0, vitd?.unit ?: ""),
    TotalNutrientsKeys.KEY_VITAMIN_K1 to Nutrient(vitk1?.label ?: "", vitk1?.quantity ?: 0.0, vitk1?.unit ?: ""),
    TotalNutrientsKeys.KEY_WATER to Nutrient(water?.label ?: "", water?.quantity ?: 0.0, water?.unit ?: ""),
    TotalNutrientsKeys.KEY_ZINC to Nutrient(zn?.label ?: "", zn?.quantity ?: 0.0, zn?.unit ?: "")
)




