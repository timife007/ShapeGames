package com.timife.shapegames.common.data.network.dtos.breeds


import com.squareup.moshi.Json

data class RemoteDogBreeds(
    @field:Json(name = "message")
    val breeds: List<String>,
    @field:Json(name = "status")
    val status: String
)