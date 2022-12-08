package com.timife.shapegames.feature_favourites.presentation.states

import com.timife.shapegames.common.domain.model.Dog

data class FavoritesState(
    val isLoading: Boolean = false,
    val favorites: List<Dog> = emptyList(),
    val error: String = "")