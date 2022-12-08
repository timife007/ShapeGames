package com.timife.shapegames.feature_breed_details.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.timife.shapegames.feature_breed_details.domain.repository.DogsRepository
import com.timife.shapegames.common.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreedDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: DogsRepository
): ViewModel() {


    private val _detailState = MutableStateFlow(DetailState(isLoading = true))
    val detailState: StateFlow<DetailState> = _detailState
    init {
        savedStateHandle.get<String>("breed")?.let {item ->
            getImages(item)
        }
    }

    fun onEvent(event: FavEvent){
        when(event){
            is FavEvent.LikeUnlike ->{
                viewModelScope.launch {
                    repository.toggleFav(event.dog,event.isFavourite,event.breed)
                }
            }
            else -> {}
        }
    }

    private fun getImages(breed:String){
            repository.getDogs(breed).onEach { resource ->
                when(resource){
                    is Resource.Success ->{
                        _detailState.value = DetailState(dogs = resource.data ?: emptyList())
                    }
                    is Resource.Error ->{
                        _detailState.value = DetailState(error = resource.message ?: "Unexpected error occurred")
                    }
                    is Resource.Loading ->{
                        _detailState.value = DetailState(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
    }

}