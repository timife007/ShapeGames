package com.timife.shapegames.feature_breed_details.presentation.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
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
import com.timife.shapegames.feature_breed_details.presentation.BreedDetailsViewModel
import com.timife.shapegames.feature_breed_details.presentation.FavEvent

@OptIn(ExperimentalLifecycleComposeApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BreedDetailsScreen(
    modifier: Modifier,
    viewModel: BreedDetailsViewModel = hiltViewModel(),
    navController: NavController
) {
    val state = viewModel.detailState.collectAsStateWithLifecycle()


    Scaffold(
        topBar = { DetailAppBar(navController) }
    ) {

        Box(modifier = modifier.fillMaxSize()) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize().padding(5.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp),
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                items(state.value.dogs) { dog ->
                    DetailListItem(
                        dog = dog.imageUrl,
                        modifier = modifier,
                        isFavourite = dog.isFavorite,
                        onCheckChange = { isFav ->
                            viewModel.onEvent(FavEvent.LikeUnlike(dog.imageUrl, isFav, dog.breed))
                        }
                    )
                }
            }

            if (state.value.error.isNotBlank()) {
                Text(
                    text = state.value.error,
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

            if (state.value.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }


}

@Composable
fun DetailAppBar(navController: NavController) {
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
                text = "Details",
                color = Color.White,
                modifier = Modifier.weight(1f),
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.h6
            )

        }
    }
}