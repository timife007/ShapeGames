package com.timife.shapegames.feature_breeds.presentation

data class BreedListState(
    val isLoading: Boolean = false,
    val breeds: List<String> = emptyList(),
    val error: String = ""
)