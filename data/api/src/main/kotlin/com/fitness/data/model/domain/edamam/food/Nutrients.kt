package com.fitness.data.model.domain.edamam.food

/**
 * @param id Nutrients Entity identifier
 * @param fId Food Entity identifier that this Nutrients Entity belongs to
 */
data class Nutrients(
    val id: String,
    val fId: String,
    val CalciumCa: Double,
    val CarbohydrateDifference: Double,
    val Cholesterol: Double,
    val Energy: Double,
    val FattyAcidsMonounsaturated: Double,
    val FattyAcidsPolyunsaturated: Double,
    val FattyAcidsSaturated: Double,
    val Fat: Double,
    val FatTrans: Double,
    val Iron: Double,
    val FiberDietary: Double,
    val Potassium: Double,
    val Magnesium: Double,
    val Sodium: Double,
    val Niacin: Double,
    val Phosphorus: Double,
    val Protein: Double,
    val Riboflavin: Double,
    val Sugar: Double,
    val Thiamin: Double,
    val VitaminB12: Double,
    val VitaminB6: Double,
    val VitaminC: Double,
    val Zinc: Double
)