package com.timife.shapegames.common.data.network

import com.timife.shapegames.common.data.network.dtos.breeds.RemoteDogBreeds
import com.timife.shapegames.common.data.network.dtos.dogs.RemoteBreedItems
import retrofit2.http.GET
import retrofit2.http.Path

interface DogApi {
    @GET("breeds/list/")
    suspend fun getDogBreeds(): RemoteDogBreeds

    @GET("breed/{breed}/images/")
    suspend fun getDogItems(
        @Path("breed") breedName:String
    ): RemoteBreedItems
}