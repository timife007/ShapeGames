package com.timife.shapegames.feature_breed_details.domain.repository

import com.timife.shapegames.common.domain.model.Dog
import com.timife.shapegames.common.utils.Resource
import kotlinx.coroutines.flow.Flow

interface DogsRepository {
    fun getDogs(breed:String): Flow<Resource<List<Dog>>>

    suspend fun toggleFav(imageUrl:String,isFav:Boolean,breed: String)
}