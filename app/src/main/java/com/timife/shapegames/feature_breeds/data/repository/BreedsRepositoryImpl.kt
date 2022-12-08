package com.timife.shapegames.feature_breeds.data.repository

import com.timife.shapegames.common.data.network.DogApi
import com.timife.shapegames.feature_breeds.domain.repository.BreedsRepository
import com.timife.shapegames.common.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class BreedsRepositoryImpl @Inject constructor(
    private val api: DogApi
) : BreedsRepository {
    override fun getBreeds(): Flow<Resource<List<String>>> {
        return flow {
            emit(Resource.Loading(isLoading = true))
            try {
                val breeds = api.getDogBreeds()
                emit(Resource.Success(breeds.breeds))
            }catch (e:IOException){
                emit(Resource.Error( "Error occurred, please check internet connection"))
            }catch (e:HttpException){
                emit(Resource.Error("Unknown error occurred"))
            }
            emit(Resource.Loading(false))
        }
    }
}