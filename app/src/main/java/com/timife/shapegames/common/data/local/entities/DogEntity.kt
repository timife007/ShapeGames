package com.timife.shapegames.common.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dog_pictures")
data class DogEntity(
    @PrimaryKey
    val imageUrl: String,
    val isFavorite:Boolean = false,
    val breed:String
)