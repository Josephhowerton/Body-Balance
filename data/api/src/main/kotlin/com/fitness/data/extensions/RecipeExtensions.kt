package com.fitness.data.extensions

import com.fitness.data.model.cache.edamam.DigestEntity
import com.fitness.data.model.cache.edamam.ImageMetaDataEntity
import com.fitness.data.model.cache.edamam.ImagesEntity
import com.fitness.data.model.cache.edamam.IngredientEntity
import com.fitness.data.model.cache.edamam.NutrientsMetaDataEntity
import com.fitness.data.model.cache.edamam.RecipeEntity
import com.fitness.data.model.cache.edamam.SubEntity
import com.fitness.data.model.cache.edamam.TotalDailyEntity
import com.fitness.data.model.cache.edamam.TotalNutrientsEntity
import com.fitness.data.model.network.edamam.recipe.DigestDto
import com.fitness.data.model.network.edamam.recipe.ImageMetaDataDto
import com.fitness.data.model.network.edamam.recipe.ImagesDto
import com.fitness.data.model.network.edamam.recipe.IngredientDto
import com.fitness.data.model.network.edamam.recipe.NutrientsMetaDataDto
import com.fitness.data.model.network.edamam.recipe.RecipeDto
import com.fitness.data.model.network.edamam.recipe.SubDto
import com.fitness.data.model.network.edamam.recipe.TotalDailyDto
import com.fitness.data.model.network.edamam.recipe.TotalNutrientsDto


@JvmName("toDomainModelFromRecipeDto")
fun RecipeDto.toCacheModel(): RecipeEntity =
    RecipeEntity(
        calories = calories,
        cautions = cautions,
        co2EmissionsClass = co2EmissionsClass,
        cuisineType = cuisineType,
        dietLabels = dietLabels,
        digest = digest?.toCacheModel(),
        dishType = dishType,
        healthLabels = healthLabels,
        image = image,
        images = images?.toCacheModel(),
        ingredientLines = ingredientLines,
        ingredients = ingredients?.toCacheModel(),
        label = label,
        mealType = mealType,
        shareAs = shareAs,
        source = source,
        tags = tags,
        totalDaily = totalDaily?.toCacheModel(),
        totalNutrients = totalNutrients?.toCacheModel(),
        totalWeight = totalWeight,
        uri = uri,
        url = url,
        yield = yield
    )


@JvmName("toCacheModelFromDigestDto")
fun DigestDto.toCacheModel(): DigestEntity =
    DigestEntity(
        daily = daily,
        hasRDI = hasRDI,
        label = label,
        schemaOrgTag = schemaOrgTag,
        sub = sub?.toCacheModel(),
        tag = tag,
        total = total,
        unit = unit
    )

@JvmName("toCacheModelFromDigestDtoList")
fun List<DigestDto>.toCacheModel(): List<DigestEntity> = map {
    it.toCacheModel()
}

@JvmName("toCacheModelListFromSubDtoList")
fun List<SubDto>.toCacheModel(): List<SubEntity> =
    map { it.toCacheModel() }


@JvmName("toCacheModelFromSubDto")
fun SubDto.toCacheModel(): SubEntity =
    SubEntity(
        daily = daily,
        hasRDI = hasRDI,
        label = label,
        schemaOrgTag = schemaOrgTag,
        tag = tag,
        total = total,
        unit = unit
    )

@JvmName("toCacheModelFromImagesDto")
fun ImagesDto.toCacheModel(): ImagesEntity =
    ImagesEntity(
        large = large?.toCacheModel(),
        regular = regular?.toCacheModel(),
        small = small?.toCacheModel(),
        thumbnail = thumbnail?.toCacheModel()
    )

@JvmName("toCacheModelFromImageMetaDataDto")
fun ImageMetaDataDto.toCacheModel(): ImageMetaDataEntity =
    ImageMetaDataEntity(
        height = height,
        url = url,
        width = width
    )

@JvmName("toCacheModelFromIngredientDto")
fun IngredientDto.toCacheModel(): IngredientEntity =
    IngredientEntity(
        food = food,
        foodId = foodId,
        measure = measure,
        quantity = quantity,
        text = text,
        weight = weight
    )

@JvmName("toCacheModelFromIngredientDtoList")
fun List<IngredientDto>.toCacheModel(): List<IngredientEntity> = map {
    it.toCacheModel()
}

@JvmName("toCacheModelFromTotalNutrientsDto")
fun TotalNutrientsDto.toCacheModel(): TotalNutrientsEntity =
    TotalNutrientsEntity(
        id = "", //TODO create a unique id
        ca = ca?.toCacheModel(),
        chocdf = chocdf?.toCacheModel(),
        chocdfnet = chocdfnet?.toCacheModel(),
        chole = chole?.toCacheModel(),
        enerckcal = enerckcal?.toCacheModel(),
        fams = fams?.toCacheModel(),
        fapu = fapu?.toCacheModel(),
        fasat = fasat?.toCacheModel(),
        fat = fat?.toCacheModel(),
        fatrn = fatrn?.toCacheModel(),
        fe = fe?.toCacheModel(),
        fibtg = fibtg?.toCacheModel(),
        folac = folac?.toCacheModel(),
        foldfe = foldfe?.toCacheModel(),
        folfd = folfd?.toCacheModel(),
        k = k?.toCacheModel(),
        mg = mg?.toCacheModel(),
        na = na?.toCacheModel(),
        nia = nia?.toCacheModel(),
        p = p?.toCacheModel(),
        procnt = procnt?.toCacheModel(),
        ribf = ribf?.toCacheModel(),
        sugar = sugar?.toCacheModel(),
        sugaradded = sugaradded?.toCacheModel(),
        thia = thia?.toCacheModel(),
        tocpaha = tocpaha?.toCacheModel(),
        vitarAE = vitarAE?.toCacheModel(),
        vitb12 = vitb12?.toCacheModel(),
        vitb6a = vitb6a?.toCacheModel(),
        vitc = vitc?.toCacheModel(),
        vitd = vitd?.toCacheModel(),
        vitk1 = vitk1?.toCacheModel(),
        water = water?.toCacheModel(),
        zn = zn?.toCacheModel(),
    )

@JvmName("toCacheModelFromTotalDailyDto")
fun TotalDailyDto.toCacheModel(): TotalDailyEntity =
    TotalDailyEntity(
        id = "", //TODO create a unique id
        ca = ca?.toCacheModel(),
        chocdf = chocdf?.toCacheModel(),
        chole = chole?.toCacheModel(),
        enerckcal = enerckcal?.toCacheModel(),
        fasat = fasat?.toCacheModel(),
        fat = fat?.toCacheModel(),
        fe = fe?.toCacheModel(),
        fibtg = fibtg?.toCacheModel(),
        foldfe = foldfe?.toCacheModel(),
        k = k?.toCacheModel(),
        mg = mg?.toCacheModel(),
        na = na?.toCacheModel(),
        nia = nia?.toCacheModel(),
        p = p?.toCacheModel(),
        procnt = procnt?.toCacheModel(),
        ribf = ribf?.toCacheModel(),
        thia = thia?.toCacheModel(),
        tocpaha = tocpaha?.toCacheModel(),
        vitarae = vitarae?.toCacheModel(),
        vitb12 = vitb12?.toCacheModel(),
        vitb6a = vitb6a?.toCacheModel(),
        vitc = vitc?.toCacheModel(),
        vitd = vitd?.toCacheModel(),
        vitk1 = vitk1?.toCacheModel(),
        zn = zn?.toCacheModel()
    )

@JvmName("toCacheModelFromNutrientsMetaDataDto")
fun NutrientsMetaDataDto.toCacheModel(): NutrientsMetaDataEntity =
    NutrientsMetaDataEntity(
        label = label,
        quantity = quantity,
        unit = unit
    )
