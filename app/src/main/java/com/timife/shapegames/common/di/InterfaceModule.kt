package com.timife.shapegames.common.di

import com.timife.shapegames.feature_breeds.data.repository.BreedsRepositoryImpl
import com.timife.shapegames.feature_breed_details.data.repository.DogsRepositoryImpl
import com.timife.shapegames.feature_favourites.data.repository.FavouritesRepositoryImpl
import com.timife.shapegames.feature_breeds.domain.repository.BreedsRepository
import com.timife.shapegames.feature_breed_details.domain.repository.DogsRepository
import com.timife.shapegames.feature_favourites.domain.repository.FavouritesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class InterfaceModule {

    @Binds
    @Singleton
    abstract fun bindDogsRepository(
        dogsRepositoryImpl: DogsRepositoryImpl
    ): DogsRepository

    @Binds
    @Singleton
    abstract fun bindBreedsRepository(
        breedsRepositoryImpl: BreedsRepositoryImpl
    ): BreedsRepository

    @Binds
    @Singleton
    abstract fun bindFavoritesRepository(
        favouritesRepositoryImpl: FavouritesRepositoryImpl
    ): FavouritesRepository
}