package com.timife.shapegames.feature_breed_details.data.repository

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.timife.shapegames.common.data.local.database.DogsDao
import com.timife.shapegames.common.data.local.database.FakeDogsDao
import com.timife.shapegames.common.data.local.entities.DogEntity
import com.timife.shapegames.common.data.mappers.toDog
import com.timife.shapegames.common.data.network.DogApi
import com.timife.shapegames.common.utils.Resource
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest

import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DogsRepositoryImplTest {

    private lateinit var repository: DogsRepositoryImpl
    private lateinit var dao: DogsDao
    private lateinit var api: DogApi

    @Before
    fun setUp() {
        dao = FakeDogsDao()
        api = mockk(relaxed = true) {
            coEvery {
                getDogBreeds()
            } returns mockk(relaxed = true)
        }
        repository = DogsRepositoryImpl(
            api, dao
        )
    }

    @Test
    fun getDogs() = runTest {

        for (i in dogs.indices) {
            dao.insertDogs(dogs[i])
        }

        repository.getDogs("chihuahua").test {
            val loading = awaitItem()
            assertThat((loading as Resource.Loading).isLoading).isTrue()
            val items = awaitItem()
            assertThat(items is Resource.Success).isTrue()
            dao.getBreedDogs("chihuahua").collect {
                assertThat(items.data).isEqualTo(it.map { dogEntity ->
                    dogEntity.toDog()
                })
            }
            val lastLoading = awaitItem()
            assertThat((lastLoading as Resource.Loading).isLoading).isFalse()
            awaitComplete()
        }

    }

    companion object {
        var dogs = listOf(
            DogEntity("chihuahua url", true, "chihuahua"),
            DogEntity("rottweiler url", false, "rottweiler"),
            DogEntity("chihuahua2 url", false, "chihuahua"),
            DogEntity("chihuahua3 url", true, "chihuahua"),
            DogEntity("rottweiler2 url", true, "rottweiler"),
            DogEntity("rottweiler3 url", true, "rottweiler"),
        )
    }
}