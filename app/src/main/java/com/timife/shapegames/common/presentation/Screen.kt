package com.timife.shapegames.common.presentation

sealed class Screen(val route:String){
    object BreedListScreen: Screen("breed_list_screen")
    object BreedDetailsScreen: Screen("breed_detail_screen")
    object FavouritesScreen: Screen("favourites_screen")
}