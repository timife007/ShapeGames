package com.timife.shapegames.feature_breed_details.presentation

sealed class FavEvent {
    data class LikeUnlike(val dog:String,val isFavourite:Boolean,val breed:String): FavEvent()
}