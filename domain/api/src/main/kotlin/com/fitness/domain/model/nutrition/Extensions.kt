package com.fitness.domain.model.nutrition

import com.fitness.data.model.cache.nutrition.IngredientEntity
import com.fitness.data.model.cache.nutrition.NutrientDetailEntity
import com.fitness.data.model.cache.nutrition.NutritionRecordEntity
import com.fitness.data.model.cache.nutrition.RecipeEntity
import com.fitness.data.model.cache.nutrition.TotalNutrientsEntity

fun NutritionRecord.toNutritionRecordEntity(): NutritionRecordEntity =
    NutritionRecordEntity(
        recordId = recordId,
        userId = userId,
        date = date,
        mealType = mealType,
        recipe = recipe.toRecipeEntity()
    )

fun NutritionRecordEntity.toNutritionRecord(): NutritionRecord =
    NutritionRecord(
        recordId = recordId,
        userId = userId,
        date = date,
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
        recipeUri = uri,
        recipeUrl = url,
        yield = yield,
        ingredients = ingredients?.map { it.toIngredientEntity() },
        totalNutrients = totalNutrients?.toTotalNutrientsEntity()
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
        uri = recipeUri,
        url = recipeUrl,
        yield = yield,
        ingredients = ingredients?.map { it.toIngredient() },
        totalNutrients = totalNutrients?.toTotalNutrients()
    )

fun Ingredient.toIngredientEntity(): IngredientEntity =
    IngredientEntity(
        foodId = foodId,
        recipeId = recipeId,
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
        qualifiedWeight = qualifiedWeight,
        qualifierLabel = qualifierLabel,
        qualifierUri = qualifierUri,
        measureUri = measureUri,
        measureWeight = measureWeight,
        measureFoodId = measureFoodId,
        nutrients = totalNutrients?.toTotalNutrientsEntity()
    )

fun IngredientEntity.toIngredient(): Ingredient =
    Ingredient(
        foodId = foodId,
        recipeId = recipeId,
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
        qualifiedWeight = qualifiedWeight,
        qualifierLabel = qualifierLabel,
        qualifierUri = qualifierUri,
        measureUri = measureUri,
        measureWeight = measureWeight,
        measureFoodId = measureFoodId,
        totalNutrients = nutrients?.toTotalNutrients()
    )

fun TotalNutrients.toTotalNutrientsEntity(): TotalNutrientsEntity =
    TotalNutrientsEntity(nutrients = this.nutrients.mapValues { it.value.toNutrientDetailEntity() })

fun TotalNutrientsEntity.toTotalNutrients(): TotalNutrients =
    TotalNutrients(nutrients = this.nutrients.mapValues { it.value.toNutrientDetail() })

fun NutrientDetail.toNutrientDetailEntity(): NutrientDetailEntity =
    NutrientDetailEntity(
        label = label,
        quantity = quantity,
        unit = unit
    )

fun NutrientDetailEntity.toNutrientDetail(): NutrientDetail =
    NutrientDetail(
        label = label,
        quantity = quantity,
        unit = unit
    )







