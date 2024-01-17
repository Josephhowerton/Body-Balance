package com.fitness.data.extensions

import cache.generateUniqueId
import com.fitness.data.model.cache.nutrition.IngredientEntity
import com.fitness.data.model.cache.nutrition.NutrientEntity
import com.fitness.data.model.cache.nutrition.RecipeEntity
import com.fitness.data.model.network.edamam.food.FoodDto
import com.fitness.data.model.network.edamam.food.MeasureDto
import com.fitness.data.model.network.edamam.food.NutrientsDto
import com.fitness.data.model.network.edamam.food.QualifierDto
import com.fitness.data.model.network.edamam.NutrientsResponse
import com.fitness.data.model.network.edamam.recipe.IngredientDto
import com.fitness.data.model.network.edamam.recipe.RecipeDto
import com.fitness.data.model.network.edamam.shared.TotalNutrientsDto
import com.fitness.data.util.TotalNutrientsKeys


fun RecipeDto.toRecipeEntity(userId: String): RecipeEntity = RecipeEntity(
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
    ingredients = ingredients?.map { it.toIngredientEntity() },
    nutrients = totalNutrients?.toTotalNutrientsEntity(),
    recipeId = generateUniqueId("$userId-$uri"),
)


fun IngredientDto.toIngredientEntity(): IngredientEntity = IngredientEntity(
    foodId = foodId,
    food = food,
    foodCategory = foodCategory,
    image = image,
    measure = measure,
    quantity = quantity,
    detailed = text,
    weight = weight,
)

fun TotalNutrientsDto.toTotalNutrientsEntity(): Map<String, NutrientEntity> =  mapOf(
        TotalNutrientsKeys.KEY_CALCIUM to NutrientEntity(ca?.label ?: "", ca?.quantity ?: 0.0, ca?.unit ?: ""),
        TotalNutrientsKeys.KEY_CARBOHYDRATES to NutrientEntity(chocdf?.label ?: "", chocdf?.quantity ?: 0.0, chocdf?.unit ?: ""),
        TotalNutrientsKeys.KEY_NET_CARBS to NutrientEntity(chocdfnet?.label ?: "", chocdfnet?.quantity ?: 0.0, chocdfnet?.unit ?: ""),
        TotalNutrientsKeys.KEY_CHOLESTEROL to NutrientEntity(chole?.label ?: "", chole?.quantity ?: 0.0, chole?.unit ?: ""),
        TotalNutrientsKeys.KEY_ENERGY to NutrientEntity(enerckcal?.label ?: "", enerckcal?.quantity ?: 0.0, enerckcal?.unit ?: ""),
        TotalNutrientsKeys.KEY_MONOUNSATURATED_FATS to NutrientEntity(fams?.label ?: "", fams?.quantity ?: 0.0, fams?.unit ?: ""),
        TotalNutrientsKeys.KEY_POLYUNSATURATED_FATS to NutrientEntity(fapu?.label ?: "", fapu?.quantity ?: 0.0, fapu?.unit ?: ""),
        TotalNutrientsKeys.KEY_SATURATED_FATS to NutrientEntity(fasat?.label ?: "", fasat?.quantity ?: 0.0, fasat?.unit ?: ""),
        TotalNutrientsKeys.KEY_TOTAL_FAT to NutrientEntity(fat?.label ?: "", fat?.quantity ?: 0.0, fat?.unit ?: ""),
        TotalNutrientsKeys.KEY_TRANS_FAT to NutrientEntity(fatrn?.label ?: "", fatrn?.quantity ?: 0.0, fatrn?.unit ?: ""),
        TotalNutrientsKeys.KEY_IRON to NutrientEntity(fe?.label ?: "", fe?.quantity ?: 0.0, fe?.unit ?: ""),
        TotalNutrientsKeys.KEY_FIBER to NutrientEntity(fibtg?.label ?: "", fibtg?.quantity ?: 0.0, fibtg?.unit ?: ""),
        TotalNutrientsKeys.KEY_FOLIC_ACID to NutrientEntity(folac?.label ?: "", folac?.quantity ?: 0.0, folac?.unit ?: ""),
        TotalNutrientsKeys.KEY_FOLATE_DFE to NutrientEntity(foldfe?.label ?: "", foldfe?.quantity ?: 0.0, foldfe?.unit ?: ""),
        TotalNutrientsKeys.KEY_FOOD_FOLATE to NutrientEntity(folfd?.label ?: "", folfd?.quantity ?: 0.0, folfd?.unit ?: ""),
        TotalNutrientsKeys.KEY_POTASSIUM to NutrientEntity(k?.label ?: "", k?.quantity ?: 0.0, k?.unit ?: ""),
        TotalNutrientsKeys.KEY_MAGNESIUM to NutrientEntity(mg?.label ?: "", mg?.quantity ?: 0.0, mg?.unit ?: ""),
        TotalNutrientsKeys.KEY_SODIUM to NutrientEntity(na?.label ?: "", na?.quantity ?: 0.0, na?.unit ?: ""),
        TotalNutrientsKeys.KEY_NIACIN to NutrientEntity(nia?.label ?: "", nia?.quantity ?: 0.0, nia?.unit ?: ""),
        TotalNutrientsKeys.KEY_PHOSPHORUS to NutrientEntity(p?.label ?: "", p?.quantity ?: 0.0, p?.unit ?: ""),
        TotalNutrientsKeys.KEY_PROTEIN to NutrientEntity(procnt?.label ?: "", procnt?.quantity ?: 0.0, procnt?.unit ?: ""),
        TotalNutrientsKeys.KEY_RIBOFLAVIN to NutrientEntity(ribf?.label ?: "", ribf?.quantity ?: 0.0, ribf?.unit ?: ""),
        TotalNutrientsKeys.KEY_SUGARS to NutrientEntity(sugar?.label ?: "", sugar?.quantity ?: 0.0, sugar?.unit ?: ""),
        TotalNutrientsKeys.KEY_ADDED_SUGARS to NutrientEntity(sugaradded?.label ?: "", sugaradded?.quantity ?: 0.0, sugaradded?.unit ?: ""),
        TotalNutrientsKeys.KEY_THIAMIN to NutrientEntity(thia?.label ?: "", thia?.quantity ?: 0.0, thia?.unit ?: ""),
        TotalNutrientsKeys.KEY_VITAMIN_E to NutrientEntity(tocpaha?.label ?: "", tocpaha?.quantity ?: 0.0, tocpaha?.unit ?: ""),
        TotalNutrientsKeys.KEY_VITAMIN_A to NutrientEntity(vitarAE?.label ?: "", vitarAE?.quantity ?: 0.0, vitarAE?.unit ?: ""),
        TotalNutrientsKeys.KEY_VITAMIN_B12 to NutrientEntity(vitb12?.label ?: "", vitb12?.quantity ?: 0.0, vitb12?.unit ?: ""),
        TotalNutrientsKeys.KEY_VITAMIN_B6 to NutrientEntity(vitb6a?.label ?: "", vitb6a?.quantity ?: 0.0, vitb6a?.unit ?: ""),
        TotalNutrientsKeys.KEY_VITAMIN_C to NutrientEntity(vitc?.label ?: "", vitc?.quantity ?: 0.0, vitc?.unit ?: ""),
        TotalNutrientsKeys.KEY_VITAMIN_D to NutrientEntity(vitd?.label ?: "", vitd?.quantity ?: 0.0, vitd?.unit ?: ""),
        TotalNutrientsKeys.KEY_VITAMIN_K1 to NutrientEntity(vitk1?.label ?: "", vitk1?.quantity ?: 0.0, vitk1?.unit ?: ""),
        TotalNutrientsKeys.KEY_WATER to NutrientEntity(water?.label ?: "", water?.quantity ?: 0.0, water?.unit ?: ""),
        TotalNutrientsKeys.KEY_ZINC to NutrientEntity(zn?.label ?: "", zn?.quantity ?: 0.0, zn?.unit ?: "")
    )


fun FoodDto.toIngredientEntity(
    measureDto: MeasureDto?,
    qualifier: QualifierDto?
): IngredientEntity = IngredientEntity(
    foodId = foodId,
    label = label,
    food = label,
    category = category,
    categoryLabel = categoryLabel,
    image = image,
    knownAs = knownAs,
    nutrients = nutrients?.toTotalNutrientsEntity(),
    measureLabel = measureDto?.label,
    measureUri = measureDto?.uri,
    measureWeight = measureDto?.weight,
    qualifierLabel = qualifier?.label,
    qualifierUri = qualifier?.uri,
    calories = nutrients?.enerckcal?.toInt(),
)

fun NutrientsDto.toTotalNutrientsEntity(): Map<String, NutrientEntity> = mapOf(
    TotalNutrientsKeys.KEY_CARBOHYDRATES to NutrientEntity(TotalNutrientsKeys.KEY_CARBOHYDRATES, chocdf ?: 0.0, ""),
    TotalNutrientsKeys.KEY_ENERGY to NutrientEntity(TotalNutrientsKeys.KEY_ENERGY, enerckcal ?: 0.0, ""),
    TotalNutrientsKeys.KEY_TOTAL_FAT to NutrientEntity(TotalNutrientsKeys.KEY_TOTAL_FAT, fat ?: 0.0,  ""),
    TotalNutrientsKeys.KEY_FIBER to NutrientEntity(TotalNutrientsKeys.KEY_FIBER, fibtg ?: 0.0,  ""),
    TotalNutrientsKeys.KEY_PROTEIN to NutrientEntity(TotalNutrientsKeys.KEY_PROTEIN, procnt ?: 0.0,  "")
    )

fun IngredientEntity.updateSelf(nutrients: NutrientsResponse?): IngredientEntity =
    copy(
        calories = nutrients?.calories,
        cautions = nutrients?.cautions,
        dietLabels = nutrients?.dietLabels,
        healthLabels = nutrients?.healthLabels,
        nutrients = nutrients?.totalNutrients?.toTotalNutrientsEntity(),
        weight = nutrients?.totalWeight,
        totalWeight = nutrients?.totalWeight
    )


