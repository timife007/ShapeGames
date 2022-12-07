package com.timife.shapegames.common.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.timife.shapegames.common.data.local.entities.DogEntity

@Database(
    entities = [DogEntity::class],
    version = 1,
    exportSchema = false
)
abstract class DogsDatabase : RoomDatabase() {
    abstract val dao: DogsDao
}