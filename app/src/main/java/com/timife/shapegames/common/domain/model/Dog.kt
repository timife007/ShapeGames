package com.timife.shapegames.common.domain.model

data class Dog(
    val imageUrl:String,
    val isFavorite:Boolean = false,
    val breed:String
)