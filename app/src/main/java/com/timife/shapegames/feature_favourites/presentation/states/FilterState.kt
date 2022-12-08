package com.timife.shapegames.feature_favourites.presentation.states

import com.timife.shapegames.feature_favourites.domain.model.Breed

data class FilterState(
    val breeds:List<Breed> = emptyList()
)