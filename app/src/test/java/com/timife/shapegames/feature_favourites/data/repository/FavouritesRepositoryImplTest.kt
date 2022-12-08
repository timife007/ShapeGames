package com.timife.shapegames.feature_favourites.data.repository

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.timife.shapegames.common.data.local.database.DogsDao
import com.timife.shapegames.common.data.local.database.FakeDogsDao
import com.timife.shapegames.common.data.local.entities.DogEntity
import com.timife.shapegames.common.data.mappers.toBreed
import com.timife.shapegames.common.data.mappers.toDog
import com.timife.shapegames.common.utils.Resource
import com.timife.shapegames.feature_breed_details.data.repository.DogsRepositoryImplTest.Companion.dogs
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class FavouritesRepositoryImplTest {
    private lateinit var repository: FavouritesRepositoryImpl
    private lateinit var dao:DogsDao

    @Before
    fun setUp() {
        dao = FakeDogsDao()
        repository = FavouritesRepositoryImpl(dao)
    }

    @Test
    fun getAllFavourites() = runTest{
        for (i in dogs.indices) {
            dao.insertDogs(dogs[i])
        }
        val expected = listOf(
            DogEntity("chihuahua url", true, "chihuahua"),
            DogEntity("chihuahua3 url", true, "chihuahua"),
            DogEntity("rottweiler2 url", true, "rottweiler"),
            DogEntity("rottweiler3 url", true, "rottweiler")
        )

        repository.getAllFavourites().test {
            val loading = awaitItem()
            assertThat((loading as Resource.Loading).isLoading).isTrue()

            val items = awaitItem()
            assertThat(items is Resource.Success).isTrue()

            assertThat(items.data).isEqualTo(expected.filter { entity ->
                entity.isFavorite
            }.map { filtered ->
                filtered.toDog()
            })
            val lastLoading = awaitItem()
            assertThat((lastLoading as Resource.Loading).isLoading).isFalse()
            awaitComplete()
        }
    }

    @Test
    fun getAllFavBreeds() = runTest {
        val expected = listOf(
            "chihuahua","rottweiler"
        )
        for (i in dogs.indices) {
            dao.insertDogs(dogs[i])
        }

        repository.getAllFavBreeds().test {
            val items = awaitItem()

            assertThat(items).isEqualTo(
                dogs.filter { entity ->
                    entity.isFavorite
                }.map { filtered ->
                    filtered.toBreed()
                }.toSet().toList()
            )
            awaitComplete()
        }
    }
}