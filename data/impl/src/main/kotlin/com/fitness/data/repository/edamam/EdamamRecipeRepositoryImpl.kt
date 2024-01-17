package com.fitness.data.repository.edamam

import com.fitness.data.cache.RecipeCache
import com.fitness.data.cache.RecipeDao
import com.fitness.data.cache.RecipeFreshnessThreshold
import com.fitness.data.cache.deserializeRecipeEntity
import com.fitness.data.cache.serializeRecipeEntity
import com.fitness.data.model.network.edamam.RecipeResponse
import com.fitness.data.model.network.edamam.params.RecipeSearchParams
import com.fitness.data.model.network.edamam.shared.PaginationDto
import com.fitness.data.model.network.edamam.recipe.RecipeDto
import com.fitness.data.network.EdamamRecipeService
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EdamamRecipeRepositoryImpl @Inject constructor(
    private val cache: RecipeDao,
    private val service: EdamamRecipeService
) : EdamamRecipeRepository {

    private var pagination: PaginationDto? = null

    override suspend fun fetchRecipes(params: RecipeSearchParams): List<RecipeDto> = coroutineScope{
        val local = getResultsFromCache()
        val currentTime = System.currentTimeMillis()

        val isFresh = local.run { isNotEmpty() && all { currentTime - it.created <= RecipeFreshnessThreshold } }

        return@coroutineScope if (isFresh) {
            local.map {
                deserializeRecipeEntity(it.json)
            }
        } else {
            this@EdamamRecipeRepositoryImpl.fetchRecipesFromBackend(params)
                .hits?.mapNotNull {
                    it.recipe
                }?.also {
                    launch {
                        clearAll()
                        cacheAutoSearchResults(it)
                    }
                } ?: emptyList()
        }
    }

    override suspend fun search(params: RecipeSearchParams): List<RecipeDto> =
        this.fetchRecipesFromBackend(params).run {
            pagination = links
            hits?.let {
                val recipes = it.mapNotNull { hit ->
                    hit.recipe
                }
                recipes
            } ?: emptyList()
        }

    private suspend fun fetchRecipesFromBackend(params: RecipeSearchParams): RecipeResponse =
        service.getRecipes(
            type = params.type,
            query = params.query,
            ingredients = params.ingredients,
            diet = params.diet,
            health = params.health,
            cuisineType = params.cuisineType,
            mealType = params.mealType,
            dishType = params.dishType,
            calories = params.calories,
            time = params.time,
            imageSize = params.imageSize,
            glycemicIndex = params.glycemicIndex,
            excluded = params.excluded,
            random = params.random,
            co2EmissionsClass = params.co2EmissionsClass,
            tag = params.tag,
            language = params.language
        )


    override suspend fun fetchRecipesByUri(
        type: String,
        uri: List<String>,
        language: String?,
    ): List<RecipeDto> = TODO()

    override suspend fun fetchRecipesById(
        type: String,
        id: String,
        language: String?,
    ): List<RecipeDto> = TODO()

    private suspend fun getResultsFromCache(): List<RecipeCache> = withContext(IO) { cache.getAll() }

    private suspend fun cacheAutoSearchResults(recipes: List<RecipeDto>) = withContext(IO) {
        recipes.forEach {
            val recipeCache = RecipeCache(
                created = System.currentTimeMillis(),
                json = serializeRecipeEntity(it)
            )
            cache.insertAll(recipeCache)
        }

    }

    private suspend fun clearAll() = withContext(IO)  { cache.clearAll() }

    private suspend fun delete(recipe: RecipeCache) = withContext(IO) { cache.delete(recipe) }

}