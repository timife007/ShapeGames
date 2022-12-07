package com.timife.shapegames.feature_breeds.domain.repository

import com.timife.shapegames.common.utils.Resource
import kotlinx.coroutines.flow.Flow

interface BreedsRepository {
    fun getBreeds(): Flow<Resource<List<String>>>
}