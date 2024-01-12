package com.fitness.data.extensions

import enums.ECuisineType
import enums.EDietaryRestrictions
import enums.EHealthLabel
import enums.safeEnumValueOf


@JvmName("toEnumNameFromEDietaryRestrictionsList")
fun List<EDietaryRestrictions>.toEnumName(): List<String> = mapNotNull {
    it.name
}

@JvmName("toEnumNameFromEHealthLabelList")
fun List<EHealthLabel>.toEnumName(): List<String> = mapNotNull {
    it.name
}

@JvmName("toEnumNameFromECuisineTypeList")
fun List<ECuisineType>.toEnumName(): List<String> = mapNotNull {
    it.name
}

fun List<EHealthLabel>.toApiParamFromEHealthLabel(): List<String> = mapNotNull {
    it.apiParameter
}

fun List<ECuisineType>.toApiParamFromECuisineType(): List<String> = mapNotNull {
    it.apiParameter
}

fun List<String>.toDietaryRestrictions(): List<EDietaryRestrictions> = mapNotNull {
    safeEnumValueOf<EDietaryRestrictions>(it)
}

fun List<String>.toHealthLabel(): List<EHealthLabel> = mapNotNull {
    safeEnumValueOf<EHealthLabel>(it)
}

fun List<String>.toCuisineType(): List<ECuisineType> = mapNotNull {
    safeEnumValueOf<ECuisineType>(it)
}


