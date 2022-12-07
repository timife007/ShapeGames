package com.timife.shapegames.common.di

import android.app.Application
import androidx.room.Room
import com.timife.shapegames.common.data.local.database.DogsDao
import com.timife.shapegames.common.data.local.database.DogsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDogsDatabase(app: Application): DogsDatabase {
        return Room.databaseBuilder(
            app,
            DogsDatabase::class.java,
            "dogs.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideDogsDao(
        dogsDatabase: DogsDatabase
    ): DogsDao {
        return dogsDatabase.dao
    }
}