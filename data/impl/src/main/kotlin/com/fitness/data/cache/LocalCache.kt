package com.fitness.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [RecipeCache::class],
    version = 2
)
abstract class LocalCache : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
}