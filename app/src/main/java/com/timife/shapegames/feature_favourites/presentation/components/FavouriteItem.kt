package com.timife.shapegames.feature_favourites.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun FavouriteItem(
    modifier: Modifier,
    dog: String,
    isFavourite: Boolean,
    onCheckChange: (Boolean) -> Unit,
    breed:String
) {
    Card {
        Column(
            modifier = modifier.fillMaxWidth()
        ) {
            Box {
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
            Row {
                Text(
                    text = breed,
                    modifier = Modifier.padding(start = 2.dp),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.h6
                )
            }
        }
    }
}