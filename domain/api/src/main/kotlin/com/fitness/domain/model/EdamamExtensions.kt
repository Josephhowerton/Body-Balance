package com.fitness.domain.model

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
import com.fitness.domain.model.edamam.Digest
import com.fitness.domain.model.edamam.Hit
import com.fitness.domain.model.edamam.ImageMetaData
import com.fitness.domain.model.edamam.Images
import com.fitness.domain.model.edamam.Ingredient
import com.fitness.domain.model.edamam.NutrientsMetaData
import com.fitness.domain.model.edamam.Recipe
import com.fitness.domain.model.edamam.Sub
import com.fitness.domain.model.edamam.TotalDaily
import com.fitness.domain.model.edamam.TotalNutrients

@JvmName("toRecipeFromHit")
fun List<Hit?>.toRecipes(): List<Recipe> = mapNotNull {
    it?.recipe
}

@JvmName("toDomainModelFromRecipeDto")
fun RecipeDto.toDomainModel(): Recipe =
    Recipe(
        calories = calories,
        cautions = cautions,
        co2EmissionsClass = co2EmissionsClass,
        cuisineType = cuisineType,
        dietLabels = dietLabels,
        digest = digest?.toDomainModel(),
        dishType = dishType,
        healthLabels = healthLabels,
        image = image,
        images = images?.toDomainModel(),
        ingredientLines = ingredientLines,
        ingredients = ingredients?.toDomainModel(),
        label = label,
        mealType = mealType,
        shareAs = shareAs,
        source = source,
        tags = tags,
        totalDaily = totalDaily?.toDomainModel(),
        totalNutrients = totalNutrients?.toDomainModel(),
        totalWeight = totalWeight,
        uri = uri,
        url = url,
        yield = yield
    )

@JvmName("toDomainModelFromRecipeDtoList")
fun List<RecipeDto>.toDomainModel(): List<Recipe> = map {
    it.toDomainModel()
}

@JvmName("toDomainModelFromRecipeEntityList")
fun List<RecipeEntity>.toDomainModel(): List<Recipe> = map {
    it.toDomainModel()
}
@JvmName("toDomainModelFromRecipeEntity")
fun RecipeEntity.toDomainModel(): Recipe =
    Recipe(
        calories = calories,
        cautions = cautions,
        co2EmissionsClass = co2EmissionsClass,
        cuisineType = cuisineType,
        dietLabels = dietLabels,
        digest = digest?.toDomainModel(),
        dishType = dishType,
        healthLabels = healthLabels,
        image = image,
        images = images?.toDomainModel(),
        ingredientLines = ingredientLines,
        ingredients = ingredients?.toDomainModel(),
        label = label,
        mealType = mealType,
        shareAs = shareAs,
        source = source,
        tags = tags,
        totalCO2Emissions = totalCO2Emissions,
        totalDaily = totalDaily?.toDomainModel(),
        totalNutrients = totalNutrients?.toDomainModel(),
        totalWeight = totalWeight,
        uri = uri,
        url = url,
        yield = yield
    )

@JvmName("toCacheModelFromRecipe")
fun Recipe.toCacheModel(): RecipeEntity =
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
        totalCO2Emissions = totalCO2Emissions,
        totalDaily = totalDaily?.toCacheModel(),
        totalNutrients = totalNutrients?.toCacheModel(),
        totalWeight = totalWeight,
        uri = uri,
        url = url,
        yield = yield
    )


@JvmName("toDomainModelFromFoodDigestDto")
fun DigestDto.toDomainModel(): Digest =
    Digest(
        daily = daily,
        hasRDI = hasRDI,
        label = label,
        schemaOrgTag = schemaOrgTag,
        sub = sub?.toDomainModel(),
        tag = tag,
        total = total,
        unit = unit
    )

@JvmName("toDomainModelFromDigestEntity")
fun DigestEntity.toDomainModel(): Digest =
    Digest(
        daily = daily,
        hasRDI = hasRDI,
        label = label,
        schemaOrgTag = schemaOrgTag,
        sub = sub?.toDomainModel(),
        tag = tag,
        total = total,
        unit = unit
    )

@JvmName("toCacheModelFromDigest")
fun Digest.toCacheModel(): DigestEntity =
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

@JvmName("toDomainModelFromDigestDtoList")
fun List<DigestDto>.toDomainModel(): List<Digest> = map {
    it.toDomainModel()
}

@JvmName("toDomainModelDigestEntityList")
fun List<DigestEntity>.toDomainModel(): List<Digest> = map {
    it.toDomainModel()
}

@JvmName("toCacheModelFromDigestList")
fun List<Digest>.toCacheModel(): List<DigestEntity> = map {
    it.toCacheModel()
}

@JvmName("toDomainModelListFromSubDtoList")
fun List<SubDto>.toDomainModel(): List<Sub> =
    map { it.toDomainModel() }



@JvmName("toDomainModelListFromSubEntityList")
fun List<SubEntity>.toDomainModel(): List<Sub> =
    map { it.toDomainModel() }

@JvmName("toCacheModelListFromSubList")
fun List<Sub>.toCacheModel(): List<SubEntity> =
    map { it.toCacheModel() }


@JvmName("toDomainModelFromSubDto")
fun SubDto.toDomainModel(): Sub =
    Sub(
        daily = daily,
        hasRDI = hasRDI,
        label = label,
        schemaOrgTag = schemaOrgTag,
        tag = tag,
        total = total,
        unit = unit
    )


@JvmName("toDomainModelFromSubEntity")
fun SubEntity.toDomainModel(): Sub =
    Sub(
        daily = daily,
        hasRDI = hasRDI,
        label = label,
        schemaOrgTag = schemaOrgTag,
        tag = tag,
        total = total,
        unit = unit
    )

@JvmName("toCacheModelFromSub")
fun Sub.toCacheModel(): SubEntity =
    SubEntity(
        daily = daily,
        hasRDI = hasRDI,
        label = label,
        schemaOrgTag = schemaOrgTag,
        tag = tag,
        total = total,
        unit = unit
    )

@JvmName("toDomainModelFromImagesDto")
fun ImagesDto.toDomainModel(): Images =
    Images(
        large = large?.toDomainModel(),
        regular = regular?.toDomainModel(),
        small = small?.toDomainModel(),
        thumbnail = thumbnail?.toDomainModel()
    )

@JvmName("toCacheModelFromImages")
fun Images.toCacheModel(): ImagesEntity =
    ImagesEntity(
        large = large?.toCacheModel(),
        regular = regular?.toCacheModel(),
        small = small?.toCacheModel(),
        thumbnail = thumbnail?.toCacheModel()
    )

@JvmName("toDomainModelFromImagesEntity")
fun ImagesEntity.toDomainModel(): Images =
    Images(
        large = large?.toDomainModel(),
        regular = regular?.toDomainModel(),
        small = small?.toDomainModel(),
        thumbnail = thumbnail?.toDomainModel()
    )

@JvmName("toDomainModelFromImageMetaDataDto")
fun ImageMetaDataDto.toDomainModel(): ImageMetaData =
    ImageMetaData(
        height = height,
        url = url,
        width = width
    )

@JvmName("toDomainModelFromImageMetaDataEntity")
fun ImageMetaDataEntity.toDomainModel(): ImageMetaData =
    ImageMetaData(
        height = height,
        url = url,
        width = width
    )

@JvmName("toCacheModelFromImageMetaData")
fun ImageMetaData.toCacheModel(): ImageMetaDataEntity =
    ImageMetaDataEntity(
        height = height,
        url = url,
        width = width
    )


@JvmName("toDomainModelFromIngredientDto")
fun IngredientDto.toDomainModel(): Ingredient =
    Ingredient(
        food = food,
        foodId = foodId,
        measure = measure,
        quantity = quantity,
        text = text,
        weight = weight
    )

@JvmName("toDomainModelFromIngredientEntity")
fun IngredientEntity.toDomainModel(): Ingredient =
    Ingredient(
        food = food,
        foodId = foodId,
        measure = measure,
        quantity = quantity,
        text = text,
        weight = weight
    )

@JvmName("toCacheModelFromIngredient")
fun Ingredient.toCacheModel(): IngredientEntity =
    IngredientEntity(
        food = food,
        foodId = foodId,
        measure = measure,
        quantity = quantity,
        text = text,
        weight = weight
    )

@JvmName("toDomainModelFromIngredientDtoList")
fun List<IngredientDto>.toDomainModel(): List<Ingredient> = map {
    it.toDomainModel()
}

@JvmName("toDomainModelFromIngredientEntityList")
fun List<IngredientEntity>.toDomainModel(): List<Ingredient> = map {
    it.toDomainModel()
}

@JvmName("toCacheModelFromIngredientList")
fun List<Ingredient>.toCacheModel(): List<IngredientEntity> = map {
    it.toCacheModel()
}

@JvmName("toDomainModelFromTotalNutrientsDto")
fun TotalNutrientsDto.toDomainModel(): TotalNutrients =
    TotalNutrients(
        ca = ca?.toDomainModel(),
        chocdf = chocdf?.toDomainModel(),
        chocdfnet = chocdfnet?.toDomainModel(),
        chole = chole?.toDomainModel(),
        enerckcal = enerckcal?.toDomainModel(),
        fams = fams?.toDomainModel(),
        fapu = fapu?.toDomainModel(),
        fasat = fasat?.toDomainModel(),
        fat = fat?.toDomainModel(),
        fatrn = fatrn?.toDomainModel(),
        fe = fe?.toDomainModel(),
        fibtg = fibtg?.toDomainModel(),
        folac = folac?.toDomainModel(),
        foldfe = foldfe?.toDomainModel(),
        folfd = folfd?.toDomainModel(),
        k = k?.toDomainModel(),
        mg = mg?.toDomainModel(),
        na = na?.toDomainModel(),
        nia = nia?.toDomainModel(),
        p = p?.toDomainModel(),
        procnt = procnt?.toDomainModel(),
        ribf = ribf?.toDomainModel(),
        sugar = sugar?.toDomainModel(),
        sugaradded = sugaradded?.toDomainModel(),
        thia = thia?.toDomainModel(),
        tocpaha = tocpaha?.toDomainModel(),
        vitarAE = vitarAE?.toDomainModel(),
        vitb12 = vitb12?.toDomainModel(),
        vitb6a = vitb6a?.toDomainModel(),
        vitc = vitc?.toDomainModel(),
        vitd = vitd?.toDomainModel(),
        vitk1 = vitk1?.toDomainModel(),
        water = water?.toDomainModel(),
        zn = zn?.toDomainModel()
    )


@JvmName("toDomainModelFromTotalNutrientsEntity")
fun TotalNutrientsEntity.toDomainModel(): TotalNutrients =
    TotalNutrients(
        ca = ca?.toDomainModel(),
        chocdf = chocdf?.toDomainModel(),
        chocdfnet = chocdfnet?.toDomainModel(),
        chole = chole?.toDomainModel(),
        enerckcal = enerckcal?.toDomainModel(),
        fams = fams?.toDomainModel(),
        fapu = fapu?.toDomainModel(),
        fasat = fasat?.toDomainModel(),
        fat = fat?.toDomainModel(),
        fatrn = fatrn?.toDomainModel(),
        fe = fe?.toDomainModel(),
        fibtg = fibtg?.toDomainModel(),
        folac = folac?.toDomainModel(),
        foldfe = foldfe?.toDomainModel(),
        folfd = folfd?.toDomainModel(),
        k = k?.toDomainModel(),
        mg = mg?.toDomainModel(),
        na = na?.toDomainModel(),
        nia = nia?.toDomainModel(),
        p = p?.toDomainModel(),
        procnt = procnt?.toDomainModel(),
        ribf = ribf?.toDomainModel(),
        sugar = sugar?.toDomainModel(),
        sugaradded = sugaradded?.toDomainModel(),
        thia = thia?.toDomainModel(),
        tocpaha = tocpaha?.toDomainModel(),
        vitarAE = vitarAE?.toDomainModel(),
        vitb12 = vitb12?.toDomainModel(),
        vitb6a = vitb6a?.toDomainModel(),
        vitc = vitc?.toDomainModel(),
        vitd = vitd?.toDomainModel(),
        vitk1 = vitk1?.toDomainModel(),
        water = water?.toDomainModel(),
        zn = zn?.toDomainModel()
    )

@JvmName("toCacheModelFromTotalNutrients")
fun TotalNutrients.toCacheModel(): TotalNutrientsEntity =
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
        zn = zn?.toCacheModel()
    )

@JvmName("toDomainModelFromTotalDailyDto")
fun TotalDailyDto.toDomainModel(): TotalDaily =
    TotalDaily(
        ca = ca?.toDomainModel(),
        chocdf = chocdf?.toDomainModel(),
        chole = chole?.toDomainModel(),
        enerckcal = enerckcal?.toDomainModel(),
        fasat = fasat?.toDomainModel(),
        fat = fat?.toDomainModel(),
        fe = fe?.toDomainModel(),
        fibtg = fibtg?.toDomainModel(),
        foldfe = foldfe?.toDomainModel(),
        k = k?.toDomainModel(),
        mg = mg?.toDomainModel(),
        na = na?.toDomainModel(),
        nia = nia?.toDomainModel(),
        p = p?.toDomainModel(),
        procnt = procnt?.toDomainModel(),
        ribf = ribf?.toDomainModel(),
        thia = thia?.toDomainModel(),
        tocpaha = tocpaha?.toDomainModel(),
        vitarae = vitarae?.toDomainModel(),
        vitb12 = vitb12?.toDomainModel(),
        vitb6a = vitb6a?.toDomainModel(),
        vitc = vitc?.toDomainModel(),
        vitd = vitd?.toDomainModel(),
        vitk1 = vitk1?.toDomainModel(),
        zn = zn?.toDomainModel()
    )


@JvmName("toDomainModelFromTotalDailyEntity")
fun TotalDailyEntity.toDomainModel(): TotalDaily =
    TotalDaily(
        ca = ca?.toDomainModel(),
        chocdf = chocdf?.toDomainModel(),
        chole = chole?.toDomainModel(),
        enerckcal = enerckcal?.toDomainModel(),
        fasat = fasat?.toDomainModel(),
        fat = fat?.toDomainModel(),
        fe = fe?.toDomainModel(),
        fibtg = fibtg?.toDomainModel(),
        foldfe = foldfe?.toDomainModel(),
        k = k?.toDomainModel(),
        mg = mg?.toDomainModel(),
        na = na?.toDomainModel(),
        nia = nia?.toDomainModel(),
        p = p?.toDomainModel(),
        procnt = procnt?.toDomainModel(),
        ribf = ribf?.toDomainModel(),
        thia = thia?.toDomainModel(),
        tocpaha = tocpaha?.toDomainModel(),
        vitarae = vitarae?.toDomainModel(),
        vitb12 = vitb12?.toDomainModel(),
        vitb6a = vitb6a?.toDomainModel(),
        vitc = vitc?.toDomainModel(),
        vitd = vitd?.toDomainModel(),
        vitk1 = vitk1?.toDomainModel(),
        zn = zn?.toDomainModel()
    )

@JvmName("toCacheModelFromTotalDaily")
fun TotalDaily.toCacheModel(): TotalDailyEntity =
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

@JvmName("toDomainModelFromNutrientsMetaDataDto")
fun NutrientsMetaDataDto.toDomainModel(): NutrientsMetaData =
    NutrientsMetaData(
        label = label,
        quantity = quantity,
        unit = unit
    )
@JvmName("toDomainModelFromNutrientsMetaDataEntity")
fun NutrientsMetaDataEntity.toDomainModel(): NutrientsMetaData =
    NutrientsMetaData(
        label = label,
        quantity = quantity,
        unit = unit
    )

@JvmName("toCacheModelFromNutrientsMetaData")
fun NutrientsMetaData.toCacheModel(): NutrientsMetaDataEntity =
    NutrientsMetaDataEntity(
        label = label,
        quantity = quantity,
        unit = unit
    )