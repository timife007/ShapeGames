package com.timife.shapegames.feature_breed_details.presentation

import com.timife.shapegames.common.domain.model.Dog

data class DetailState(
    val isLoading: Boolean = false,
    val dogs: List<Dog> = emptyList(),
    val error: String = ""
)