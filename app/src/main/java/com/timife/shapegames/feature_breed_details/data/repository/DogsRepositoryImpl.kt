package com.timife.shapegames.feature_breed_details.data.repository

import com.timife.shapegames.common.data.local.database.DogsDao
import com.timife.shapegames.common.data.local.entities.DogEntity
import com.timife.shapegames.common.data.mappers.toDog
import com.timife.shapegames.common.data.network.DogApi
import com.timife.shapegames.common.domain.model.Dog
import com.timife.shapegames.feature_breed_details.domain.repository.DogsRepository
import com.timife.shapegames.common.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DogsRepositoryImpl @Inject constructor(
    private val api: DogApi,
    private val dao: DogsDao
) : DogsRepository {
    override fun getDogs(breed: String): Flow<Resource<List<Dog>>> {
        return flow {
            emit(Resource.Loading(true))
            try {
                val items = api.getDogItems(breed)
                items.let { remote ->
                    val localDogs = remote.dogs.map {
                        DogEntity(imageUrl = it, false,breed = breed)
                    }
                    localDogs.map {
                        dao.insertDogs(it)
                    }
                    dao.getBreedDogs(breed).collect{
                        emit(Resource.Success(it.map { entity ->
                            entity.toDog()
                        }))
                    }
                }
            } catch (e: IOException) {
                emit(
                    Resource.Error(
                        e.message ?: "Unexpected error occurred. Please check internet connection"
                    )
                )
            } catch (e: HttpException) {
                emit(Resource.Error(e.localizedMessage ?: "Unexpected error occurred"))
            }
        }
    }

    override suspend fun toggleFav(imageUrl: String, isFav: Boolean,breed:String) {
        dao.updateDog(DogEntity(imageUrl = imageUrl, isFavorite = isFav,breed))
    }
}