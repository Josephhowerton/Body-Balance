package com.fitness.data.extensions

import com.fitness.data.model.cache.edamam.food.FoodEntity
import com.fitness.data.model.cache.edamam.food.MeasureEntity
import com.fitness.data.model.cache.edamam.food.NutrientsEntity
import com.fitness.data.model.cache.edamam.food.QualifiedEntity
import com.fitness.data.model.cache.edamam.food.QualifierEntity
import com.fitness.data.model.cache.edamam.food.ServingSizeEntity
import com.fitness.data.model.domain.edamam.food.Food
import com.fitness.data.model.domain.edamam.food.Measure
import com.fitness.data.model.domain.edamam.food.Nutrients
import com.fitness.data.model.domain.edamam.food.Qualified
import com.fitness.data.model.domain.edamam.food.Qualifier
import com.fitness.data.model.domain.edamam.food.ServingSize

fun FoodEntity.toFood() =
    Food(
        id = id,
        apiId = apiId,
        mId = mId,
        food = food,
        label = label,
        knownAs = knownAs,
        image = image,
        brand = brand,
        category = category,
        categoryLabel = categoryLabel,
        foodContentsLabel = foodContentsLabel,
        quantity = quantity,
        measure = measure,
        measureURI = measureURI,
        weight = weight,
        retainedWeight = retainedWeight,
        servingSizes = servingSizes,
        servingsPerContainer = servingsPerContainer,
        dietLabels = dietLabels,
        healthLabels = healthLabels,
        cautions = cautions
    )

fun MeasureEntity.toMeasure() =
    Measure(
        id = id,
        fId = fId,
        unit = unit,
        unitUri = unitUri,
        weight = weight,
        qualified = qualified.toQualifiedList()
    )

fun NutrientsEntity.toNutrients() =
    Nutrients(
        id = id,
        fId = fId,
        CalciumCa = CalciumCa,
        CarbohydrateDifference = CarbohydrateDifference,
        Cholesterol = Cholesterol,
        Energy = Energy,
        FattyAcidsMonounsaturated = FattyAcidsMonounsaturated,
        FattyAcidsPolyunsaturated = FattyAcidsPolyunsaturated,
        FattyAcidsSaturated = FattyAcidsSaturated,
        Fat = Fat,
        FatTrans = FatTrans,
        Iron = Iron,
        FiberDietary = FiberDietary,
        Potassium = Potassium,
        Magnesium = Magnesium,
        Sodium = Sodium,
        Niacin = Niacin,
        Phosphorus = Phosphorus,
        Protein = Protein,
        Riboflavin = Riboflavin,
        Sugar = Sugar,
        Thiamin = Thiamin,
        VitaminB12 = VitaminB12,
        VitaminB6 = VitaminB6,
        VitaminC = VitaminC,
        Zinc = Zinc,
    )

fun QualifiedEntity.toQualified() =
    Qualified(
        qualifiers = qualifiers.toQualifierList(),
        weight = weight
    )

fun List<QualifiedEntity>.toQualifiedList() = map {
    it.toQualified()
}

fun QualifierEntity.toQualifier() =
    Qualifier(
        label = label,
        uri = uri
    )

fun List<QualifierEntity>.toQualifierList() = map {
    it.toQualifier()
}

fun ServingSizeEntity.toServingSize() =
    ServingSize(
        unit = unit,
        quantity = quantity,
        unitUri = unitUri
    )

fun List<ServingSizeEntity>.toServingSizeList() = map {
    it.toServingSize()
}

fun Food.toFoodEntity() =
    FoodEntity(
        id = id,
        apiId = apiId,
        mId = mId,
        food = food,
        label = label,
        knownAs = knownAs,
        image = image,
        brand = brand,
        category = category,
        categoryLabel = categoryLabel,
        foodContentsLabel = foodContentsLabel,
        quantity = quantity,
        measure = measure,
        measureURI = measureURI,
        weight = weight,
        retainedWeight = retainedWeight,
        servingSizes = servingSizes,
        servingsPerContainer = servingsPerContainer,
        dietLabels = dietLabels,
        healthLabels = healthLabels,
        cautions = cautions
    )

fun Measure.toMeasureEntity() =
    MeasureEntity(
        id = id,
        fId = fId,
        unit = unit,
        unitUri = unitUri,
        weight = weight,
        qualified = qualified.toQualifiedEntityList()
    )

fun Nutrients.toNutrientsEntity() =
    NutrientsEntity(
        id = id,
        fId = fId,
        CalciumCa = CalciumCa,
        CarbohydrateDifference = CarbohydrateDifference,
        Cholesterol = Cholesterol,
        Energy = Energy,
        FattyAcidsMonounsaturated = FattyAcidsMonounsaturated,
        FattyAcidsPolyunsaturated = FattyAcidsPolyunsaturated,
        FattyAcidsSaturated = FattyAcidsSaturated,
        Fat = Fat,
        FatTrans = FatTrans,
        Iron = Iron,
        FiberDietary = FiberDietary,
        Potassium = Potassium,
        Magnesium = Magnesium,
        Sodium = Sodium,
        Niacin = Niacin,
        Phosphorus = Phosphorus,
        Protein = Protein,
        Riboflavin = Riboflavin,
        Sugar = Sugar,
        Thiamin = Thiamin,
        VitaminB12 = VitaminB12,
        VitaminB6 = VitaminB6,
        VitaminC = VitaminC,
        Zinc = Zinc,
    )

fun Qualified.toQualifiedEntity() =
    QualifiedEntity(
        qualifiers = qualifiers.toQualifierEntityList(),
        weight = weight
    )

fun List<Qualified>.toQualifiedEntityList() = map {
    it.toQualifiedEntity()
}

fun Qualifier.toQualifierEntity() =
    QualifierEntity(
        label = label,
        uri = uri
    )

fun List<Qualifier>.toQualifierEntityList() = map {
    it.toQualifierEntity()
}

fun ServingSize.toServingSizeEntity() =
    ServingSizeEntity(
        unit = unit,
        quantity = quantity,
        unitUri = unitUri
    )

fun List<ServingSize>.toServingSizeEntityList() = map {
    it.toServingSizeEntity()
}