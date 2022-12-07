package com.timife.shapegames.feature_favourites.data.repository

import com.timife.shapegames.common.data.local.database.DogsDao
import com.timife.shapegames.common.data.mappers.toDog
import com.timife.shapegames.common.domain.model.Dog
import com.timife.shapegames.feature_favourites.domain.repository.FavouritesRepository
import com.timife.shapegames.common.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FavouritesRepositoryImpl @Inject constructor(
    private val dao: DogsDao
) : FavouritesRepository {
    override  fun getAllFavourites(): Flow<Resource<List<Dog>>> {
        return flow {
            emit(Resource.Loading(true))
            dao.getAllDogs().collect {
                val list =  it.filter { entity->
                   entity.isFavorite
               }.map { filtered ->
                   filtered.toDog()
                }
                if(list.isNotEmpty()){
                    emit(Resource.Success(list))
                }else{
                    emit(Resource.Error("You currently have no favourite pets"))
                }
            }
        }
    }
}