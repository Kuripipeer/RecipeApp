package org.lasalle.recipeapp.ui.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.lasalle.recipeapp.models.Recipe
import org.lasalle.recipeapp.ui.RecipeTheme
import org.lasalle.recipeapp.ui.screens.home.HomeScreen

@Composable
fun RecipeCard(recipe: Recipe, onClick:() -> Unit) {
    val colors = MaterialTheme.colorScheme
    Box(
        modifier = Modifier
            .height(300.dp)
            .width(250.dp)
            .clip(RoundedCornerShape(25.dp))
            .background(Color.White)
            .clickable{
                onClick()
            }
    ){
        AsyncImage(
            model = recipe.imageUrl,
            contentDescription = "Recipe Image",
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(25.dp))
                .background(Color.LightGray),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.3f))
                .padding(15.dp)
        ) {
            Row(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(colors.primary)
                    .padding(5.dp),
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Star Icon",
                    tint = colors.onSurface
                )
                Text(
                    text = "${recipe.stars}",
                    color = colors.onSurface
                )
                Icon(
                    imageVector = Icons.Default.Schedule,
                    contentDescription = "Schedule Icon",
                    tint = colors.onSurface
                )
                Text(
                    text = "${recipe.minutes} min",
                    color = colors.onSurface
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = recipe.title,
                    color = Color.White
                )
                Text(
                    text = recipe.category,
                    color = Color.White
                )
            }
        }
    }
}

@Preview
@Composable
fun RecipeCardPreview() {
    RecipeTheme {
        RecipeCard(
            recipe = Recipe(
                id = 1,
                title = "Spaghetti Bolognese",
                ingredients = "Spaghetti, Ground Beef, Tomato Sauce, Onions, Garlic, Olive Oil, Salt, Pepper"
                    .split(", "),
                instructions = "1. Cook spaghetti according to package instructions.\n2. In a pan, heat olive oil and sauté onions and garlic until translucent.\n3. Add ground beef and cook until browned.\n4. Pour in tomato sauce and let it simmer for 15 minutes.\n5. Season with salt and pepper to taste.\n6. Serve sauce over spaghetti."
                    .split("\n"),
                imageUrl = "https://example.com/spaghetti.jpg",
                minutes = 30,
                stars = 5,
                category = "Pasta",
                userId = 1,
            ),
            onClick = { /*TODO*/ }
            )
    }
}