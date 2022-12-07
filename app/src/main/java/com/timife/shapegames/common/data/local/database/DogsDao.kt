package com.timife.shapegames.common.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.timife.shapegames.common.data.local.entities.DogEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DogsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDogs(
        item: DogEntity
    )

    @Update
    suspend fun updateDog(entity: DogEntity)


    @Query("SELECT * FROM dog_pictures WHERE breed = :breed")
    fun getBreedDogs(breed:String): Flow<List<DogEntity>>

    @Query("SELECT * FROM dog_pictures")
    fun getAllDogs(): Flow<List<DogEntity>>


    @Query("DELETE FROM dog_pictures WHERE :imageUrl = imageUrl ")
    suspend fun deleteDog(imageUrl:String)
}