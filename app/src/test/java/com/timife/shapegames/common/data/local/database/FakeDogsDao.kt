package com.timife.shapegames.common.data.local.database

import com.timife.shapegames.common.data.local.entities.DogEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeDogsDao() : DogsDao {

    var database = emptyList<DogEntity>()
    override suspend fun insertDogs(item: DogEntity) {
        database = database + item
    }

    override suspend fun updateDog(entity: DogEntity) {
        //TODO
    }

    override fun getBreedDogs(breed: String): Flow<List<DogEntity>> {
        return flow {
            emit(database.filter {
                it.breed == breed
            })
        }
    }

    override fun getAllDogs(): Flow<List<DogEntity>> {
        return flow {
            emit(database)
        }
    }

    override suspend fun deleteDog(imageUrl: String) {
        database = emptyList()
    }

}