package com.timife.shapegames.feature_favourites.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.timife.shapegames.feature_breed_details.domain.repository.DogsRepository
import com.timife.shapegames.feature_favourites.domain.repository.FavouritesRepository
import com.timife.shapegames.common.utils.Resource
import com.timife.shapegames.feature_breed_details.presentation.FavEvent
import com.timife.shapegames.feature_favourites.presentation.states.FavoritesState
import com.timife.shapegames.feature_favourites.presentation.states.FilterState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val repository: FavouritesRepository,
    private val detailRepo: DogsRepository
) : ViewModel() {
    private val _state = MutableStateFlow(FavoritesState())
    val state: StateFlow<FavoritesState> = _state

    private val _breedState = MutableStateFlow(FilterState())
    val breedState: StateFlow<FilterState> = _breedState

    init {
        getFavourites()
        getBreeds()
    }

    fun onEvent(event: FavEvent) {
        when (event) {
            is FavEvent.LikeUnlike -> {
                viewModelScope.launch {
                    detailRepo.toggleFav(event.dog, event.isFavourite, event.breed)
                }
            }
            is FavEvent.Filter -> {
                viewModelScope.launch {
                    repository.getAllFavourites().collect {
                        it.data?.filter { dog ->
                            dog.breed == event.breed
                        }?.let { dogs ->
                            _state.value = state.value.copy(favorites = dogs)
                        }
                    }
                }
            }
        }
    }

    private fun getFavourites() {
        repository.getAllFavourites().onEach { resource ->
            when (resource) {
                is Resource.Loading -> {
                    _state.value = state.value.copy(isLoading = true)
                }
                is Resource.Error -> {
                    _state.value = state.value.copy(
                        isLoading = false,
                        error = resource.message ?: "No favourite items"
                    )
                }
                is Resource.Success -> {
                    _state.value = state.value.copy(
                        favorites = resource.data ?: emptyList(),
                        isLoading = false
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getBreeds() {
        repository.getAllFavBreeds().onEach {
            _breedState.value = breedState.value.copy(breeds = it)
        }.launchIn(viewModelScope)
    }
}