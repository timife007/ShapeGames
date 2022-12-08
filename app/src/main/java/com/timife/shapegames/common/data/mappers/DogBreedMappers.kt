package com.timife.shapegames.common.data.mappers

import com.timife.shapegames.common.data.local.entities.DogEntity
import com.timife.shapegames.feature_favourites.domain.model.Breed
import com.timife.shapegames.common.domain.model.Dog

fun DogEntity.toDog(): Dog {
    return Dog(
        imageUrl = imageUrl,
        isFavorite = isFavorite,
        breed = breed
    )
}

fun DogEntity.toBreed(): Breed {
    return Breed(
        breed = breed
    )
}