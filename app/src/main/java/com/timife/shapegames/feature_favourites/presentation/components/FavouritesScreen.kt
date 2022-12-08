package com.timife.shapegames.feature_favourites.presentation.components

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.timife.shapegames.feature_breed_details.presentation.FavEvent
import com.timife.shapegames.feature_favourites.presentation.FavouritesViewModel

@OptIn(ExperimentalLifecycleComposeApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FavouritesScreen(
    modifier: Modifier,
    viewModel: FavouritesViewModel = hiltViewModel(),
    navController: NavController
) {
    val state = viewModel.state.collectAsStateWithLifecycle().value
    val breedState = viewModel.breedState.collectAsStateWithLifecycle().value
    val name =  remember { mutableStateOf("") }

    Scaffold(
        topBar = { FavAppBar(navController) }
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp),
            horizontalAlignment = Alignment.Start
        ) {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                modifier = Modifier.padding(start = 5.dp, end = 5.dp)
            ) {
                items(breedState.breeds) { item ->
                    BreedChip(breed = item.breed, onSelectionChange = {
                        viewModel.onEvent(FavEvent.Filter(it))
                        name.value = it
                    }, isSelected = item.breed == name.value )
                }
            }

            Box(modifier = Modifier.fillMaxSize()) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(5.dp),
                    verticalArrangement = Arrangement.spacedBy(5.dp),
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    items(state.favorites) { fav ->
                        FavouriteItem(
                            modifier = modifier,
                            dog = fav.imageUrl,
                            isFavourite = fav.isFavorite,
                            onCheckChange = { isFav ->
                                viewModel.onEvent(
                                    FavEvent.LikeUnlike(
                                        fav.imageUrl,
                                        isFav,
                                        fav.breed
                                    )
                                )
                            }, breed = fav.breed
                        )
                    }
                }

                if (state.error.isNotBlank()) {
                    Text(
                        text = state.error,
                        color = MaterialTheme.colors.error,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .align(
                                Alignment.Center
                            )
                    )
                }

                if (state.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
        }
    }
}

@Composable
fun FavAppBar(navController: NavController) {
    Surface(modifier = Modifier.background(Color.Transparent)) {
        Row(
            modifier = Modifier
                .background(MaterialTheme.colors.secondary)
                .fillMaxWidth()
                .height(50.dp)
                .padding(start = 10.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                navController.navigateUp()
            }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Favorites",
                    modifier = Modifier.size(30.dp),
                    tint = Color.White
                )
            }
            Text(
                text = "Favourites",
                color = Color.White,
                modifier = Modifier.weight(1f),
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.h6
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BreedChip(breed: String,isSelected:Boolean = false, onSelectionChange: (String) -> Unit) {
    Chip(
        onClick = { onSelectionChange(breed) },
        border = BorderStroke(
            ChipDefaults.OutlinedBorderSize,
            Color.Gray
        ),
        colors = ChipDefaults.chipColors(
            backgroundColor = if(isSelected) Color.Gray else MaterialTheme.colors.surface,
            contentColor = if(isSelected) Color.White else Color.Black
        )
    ) {
        Text(breed)
    }
}
