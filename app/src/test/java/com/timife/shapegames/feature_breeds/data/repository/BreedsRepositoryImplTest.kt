package com.timife.shapegames.feature_breeds.data.repository

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.timife.shapegames.common.data.network.DogApi
import com.timife.shapegames.common.data.network.dtos.breeds.RemoteDogBreeds
import com.timife.shapegames.common.utils.Resource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest

import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BreedsRepositoryImplTest {
    private lateinit var api : DogApi
    private lateinit var repository : BreedsRepositoryImpl

    @Before
    fun setUp(){
        api = mockk(relaxed = true){
            coEvery {
                getDogBreeds()
            } returns buildBreeds()
        }
        repository = BreedsRepositoryImpl(
            api
        )
    }

    @Test
    fun `check if returned breeds from api`() = runTest{

        repository.getBreeds().test {
            val loading = awaitItem()

            assertThat((loading as Resource.Loading).isLoading).isTrue()
            val remoteItems = awaitItem()
            assertThat(remoteItems is Resource.Success).isTrue()
            assertThat(remoteItems.data).isEqualTo(listOf("chihuahua","rottweiler"))

            val finishLoading = awaitItem()
            assertThat((finishLoading as Resource.Loading).isLoading).isFalse()
            awaitComplete()
        }
    }

    companion object{
        fun buildBreeds() = RemoteDogBreeds(
            listOf("chihuahua","rottweiler"),
            "successful"
        )
    }


}