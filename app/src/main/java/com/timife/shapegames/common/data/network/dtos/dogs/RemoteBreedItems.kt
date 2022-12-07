package com.timife.shapegames.common.data.network.dtos.dogs


import com.squareup.moshi.Json

data class RemoteBreedItems(
    @field:Json(name = "message")
    val dogs: List<String>,
    @field:Json(name = "status")
    val status: String
)