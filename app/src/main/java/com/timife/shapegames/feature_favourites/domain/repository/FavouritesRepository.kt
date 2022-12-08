package com.timife.shapegames.feature_favourites.domain.repository

import com.timife.shapegames.feature_favourites.domain.model.Breed
import com.timife.shapegames.common.domain.model.Dog
import com.timife.shapegames.common.utils.Resource
import kotlinx.coroutines.flow.Flow

interface FavouritesRepository {
    fun getAllFavourites(): Flow<Resource<List<Dog>>>

    fun getAllFavBreeds(): Flow<List<Breed>>
}