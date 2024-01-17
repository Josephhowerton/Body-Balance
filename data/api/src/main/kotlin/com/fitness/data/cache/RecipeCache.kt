package com.fitness.data.cache

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RecipeCache(@PrimaryKey(autoGenerate = true) val id: Int? = null, val created: Long, val json: String)