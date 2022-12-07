package com.timife.shapegames.feature_breeds.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.timife.shapegames.feature_breeds.domain.repository.BreedsRepository
import com.timife.shapegames.common.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class BreedListViewModel @Inject constructor(
    private val repository: BreedsRepository
) : ViewModel() {
    private val _breedState = mutableStateOf(BreedListState())
    val breedState: State<BreedListState> = _breedState

    init {
        getBreeds()
    }

    private fun getBreeds(){
        repository.getBreeds().onEach { breeds ->
            when (breeds) {
                is Resource.Success -> {
                    _breedState.value = BreedListState(breeds = breeds.data ?: emptyList())
                }
                is Resource.Error -> {
                    _breedState.value = BreedListState(
                        error = breeds.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _breedState.value = BreedListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}