package com.timife.shapegames.common.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.timife.shapegames.feature_breed_details.presentation.components.BreedDetailsScreen
import com.timife.shapegames.feature_breeds.presentation.components.BreedListScreen
import com.timife.shapegames.feature_favourites.presentation.components.FavouritesScreen
import com.timife.shapegames.common.presentation.ui.theme.ShapeGamesTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShapeGamesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.BreedListScreen.route
                    ) {
                        composable(route = Screen.BreedListScreen.route) {
                            BreedListScreen(navController)
                        }
                        composable(
                            route = Screen.BreedDetailsScreen.route + "/{breed}",
                            arguments = listOf(
                                navArgument(
                                    name = "breed"
                                ) {
                                    type = NavType.StringType
                                    defaultValue = ""
                                })
                        ) {
                            BreedDetailsScreen(modifier = Modifier,navController = navController)
                        }
                        composable(route = Screen.FavouritesScreen.route) {
                            FavouritesScreen(Modifier,navController = navController)
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ShapeGamesTheme {
        Greeting("Android")
    }
}