package com.timife.shapegames.feature_breeds.presentation.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.timife.shapegames.feature_breeds.presentation.BreedListViewModel
import com.timife.shapegames.common.presentation.Screen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BreedListScreen(
    navController: NavController,
    viewModel: BreedListViewModel = hiltViewModel()
) {
    val state = viewModel.breedState.value

    Scaffold(
        topBar = { TopAppBar(navController) }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.breeds) { breed ->
                    BreedListItem(breed = breed,Modifier.clickable {
                        navController.navigate(Screen.BreedDetailsScreen.route + "/$breed")
                    })
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

@Composable
fun TopAppBar(navController: NavController) {
    Surface(modifier = Modifier.background(Color.Transparent)) {
        Row(
            modifier = Modifier
                .background(MaterialTheme.colors.secondary)
                .fillMaxWidth()
                .height(50.dp).padding(start = 10.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Shape Games",
                color = Color.White,
                modifier = Modifier.weight(1f),
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.h6
            )
            IconButton(onClick = {
                navController.navigate(Screen.FavouritesScreen.route)
            }) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "Favorites",
                    modifier = Modifier.size(30.dp),
                    tint = Color.White
                )
            }
        }
    }
}