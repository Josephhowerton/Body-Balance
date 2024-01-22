package com.fitness.domain.model.nutrition


data class Ingredient(
    val name: String? = null,
    val foodId: String? = null,
    val image: String? = null,
    val quantity: Double? = null,
    val detailed: String? = null,
    val weight: Double? = null,
    val uri: String? = null,
    val category: String? = null,
    val categoryName: String? = null,
    val cautions: List<String>? = null,
    val dietLabels: List<String>? = null,
    val healthLabels: List<String>? = null,
    val totalWeight: Double? = null,
    val qualifiedUri: String? = null, // The qualified uri that corresponds with the name for selected qualifier. Value determined based on what qualified name user chooses.
    val qualifiedName: String? = null, // if available represents teh different states the ingredient can be in which affect the final weight. For instance cheese can be sliced, shredded, melted etc
    val qualifiedWeight: Double? = null, // The qualified weight for selected qualifier. Value determined based on what qualifier the user chooses.
    val measureUri: String? = null, // The measure uri that corresponds with the name (cup, tablespoon, etc) for selected measure. Value determined based on what measure name user chooses.
    val measureName: String? = null, // The measure name (cup, tablespoon, etc) for selected measure. Value determined by user.
    val measureWeight: Double? = null, // The measure weight for selected measure. Value determined based on what measure name user chooses.
    val measures: List<Measure>? = null,
    val nutrients: Map<String, Nutrient>? = null
)
