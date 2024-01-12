package com.fitness.data.cache

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RecipeCache(@PrimaryKey val id: String, val created: Long, val json: String)