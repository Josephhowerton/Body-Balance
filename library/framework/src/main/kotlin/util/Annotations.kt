package util

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RecipeSearchUseCase

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RecipeFetchUseCase