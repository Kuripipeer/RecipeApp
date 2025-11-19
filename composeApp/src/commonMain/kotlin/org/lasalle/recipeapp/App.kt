package org.lasalle.recipeapp

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.lasalle.recipeapp.ui.HomeScreenRoute
import org.lasalle.recipeapp.ui.LoginScreenRoute
import org.lasalle.recipeapp.ui.RecipeTheme
import org.lasalle.recipeapp.ui.RegisterScreenRoute
import org.lasalle.recipeapp.ui.screens.auth.LoginScreen
import org.lasalle.recipeapp.ui.screens.auth.RegisterScreen
import org.lasalle.recipeapp.ui.screens.home.HomeScreen

@Composable
@Preview
fun App() {
    RecipeTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = LoginScreenRoute
        ){
            composable<LoginScreenRoute> {
                LoginScreen(
                    navController = navController
                )
            }
            composable<RegisterScreenRoute> {
                RegisterScreen(
                    navController = navController
                )
            }
            composable<HomeScreenRoute> {
                HomeScreen(
                    navController = navController
                )
            }
        }
    }
}