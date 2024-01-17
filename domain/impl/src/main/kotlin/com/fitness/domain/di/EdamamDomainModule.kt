package com.fitness.domain.di

import com.fitness.data.repository.edamam.EdamamAutoCompleteRepository
import com.fitness.data.repository.edamam.EdamamFoodRepository
import com.fitness.data.repository.edamam.EdamamRecipeRepository
import com.fitness.domain.usecase.search.EdamamAutoCompleteUseCase
import com.fitness.domain.usecase.search.EdamamAutoCompleteUseCaseImpl
import com.fitness.domain.usecase.search.EdamamFetchAllIngredientsUseCase
import com.fitness.domain.usecase.search.EdamamFetchAllIngredientsUseCaseImpl
import com.fitness.domain.usecase.search.EdamamFetchRecipesUseCaseImpl
import com.fitness.domain.usecase.search.EdamamIngredientSearchUseCase
import com.fitness.domain.usecase.search.EdamamIngredientSearchUseCaseImpl
import com.fitness.domain.usecase.search.EdamamRecipeSearchUseCase
import com.fitness.domain.usecase.search.EdamamRecipeSearchUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import util.RecipeFetchUseCase
import util.RecipeSearchUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class EdamamDomainModule {
    @Provides
    @Singleton
    fun provideEdamamAutoCompleteUseCase(repository: EdamamAutoCompleteRepository): EdamamAutoCompleteUseCase =
        EdamamAutoCompleteUseCaseImpl(repository)

    @Provides
    @Singleton
    @RecipeSearchUseCase
    fun provideEdamamRecipeSearchUseCase(repository: EdamamRecipeRepository): EdamamRecipeSearchUseCase =
        EdamamRecipeSearchUseCaseImpl(repository)

    @Provides
    @Singleton
    @RecipeFetchUseCase
    fun provideEdamamFetchRecipesUseCase(repository: EdamamRecipeRepository): EdamamRecipeSearchUseCase =
        EdamamFetchRecipesUseCaseImpl(repository)
    @Provides
    @Singleton
    fun provideEdamamFoodSearchUseCase(repository: EdamamFoodRepository): EdamamIngredientSearchUseCase =
        EdamamIngredientSearchUseCaseImpl(repository)

    @Provides
    @Singleton
    fun provideEdamamFetchAllIngredientsUseCaseImpl(repository: EdamamFoodRepository): EdamamFetchAllIngredientsUseCase = EdamamFetchAllIngredientsUseCaseImpl(repository)
}

