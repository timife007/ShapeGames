package com.timife.shapegames.feature_breed_details.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun DetailListItem(
    modifier: Modifier,
    dog: String,
    isFavourite: Boolean,
    onCheckChange: (Boolean) -> Unit
) {
    Card() {
        Box() {
            AsyncImage(
                model = dog, contentDescription = "Poster Image",
                modifier = Modifier
                    .height(140.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            IconToggleButton(
                checked = isFavourite,
                onCheckedChange = onCheckChange,
                modifier = Modifier.align(Alignment.BottomEnd)
            ) {
                Icon(
                    tint = Color.White,
                    modifier = modifier.graphicsLayer {
                        scaleX = 1.3f
                        scaleY = 1.3f
                    },
                    imageVector = if (isFavourite) {
                        Icons.Filled.Favorite
                    } else {
                        Icons.Default.FavoriteBorder
                    },
                    contentDescription = null
                )
            }

        }
    }

}