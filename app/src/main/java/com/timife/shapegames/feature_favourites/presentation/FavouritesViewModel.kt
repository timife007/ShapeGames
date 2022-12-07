package com.timife.shapegames.feature_favourites.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.timife.shapegames.feature_breed_details.domain.repository.DogsRepository
import com.timife.shapegames.feature_favourites.domain.repository.FavouritesRepository
import com.timife.shapegames.common.utils.Resource
import com.timife.shapegames.feature_breed_details.presentation.FavEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val repository: FavouritesRepository,
    private val detailRepo: DogsRepository
) : ViewModel() {
    private val _state = mutableStateOf(FavoritesState())
    val state : State<FavoritesState> = _state

    init {
        getFavourites()
    }

    fun onEvent(event: FavEvent){
        when(event){
            is FavEvent.LikeUnlike ->{
                viewModelScope.launch {
                    detailRepo.toggleFav(event.dog,event.isFavourite,event.breed)
                }
            }
        }
    }

    private fun getFavourites(){
        repository.getAllFavourites().onEach {resource ->
            when(resource){
                is Resource.Loading ->{
                    _state.value = state.value.copy(isLoading = true)
                }
                is Resource.Error ->{
                    _state.value = state.value.copy(isLoading = false, error = resource.message ?: "No favourite items")
                }
                is Resource.Success ->{
                    _state.value = state.value.copy(favorites = resource.data ?: emptyList(), isLoading = false)
                }
            }
        }.launchIn(viewModelScope)
    }
}