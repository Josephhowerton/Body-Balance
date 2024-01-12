package com.fitness.data.cache

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.fitness.data.model.cache.edamam.RecipeEntity

@Dao
interface RecipeDao {
    @Query("SELECT * FROM RecipeCache")
    suspend fun getAll(): List<RecipeCache>

    @Insert
    suspend fun insertAll(vararg recipes: RecipeCache)

    @Delete
    suspend fun delete(recipe: RecipeCache)

    @Query("DELETE FROM RecipeCache")
    suspend fun clearAll()
}