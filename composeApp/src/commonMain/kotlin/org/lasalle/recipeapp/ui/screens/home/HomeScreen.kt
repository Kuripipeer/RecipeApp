package org.lasalle.recipeapp.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.lasalle.recipeapp.models.RecipePreview
import org.lasalle.recipeapp.ui.RecipeTheme
import org.lasalle.recipeapp.ui.screens.home.components.LoadingOverlay
import org.lasalle.recipeapp.ui.screens.home.components.RecipeCard
import org.lasalle.recipeapp.ui.screens.home.components.RecipeListItem
import org.lasalle.recipeapp.ui.viewmodels.HomeViewModel
import org.lasalle.recipeapp.utils.hideKeyboard
import kotlin.text.category

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(){
    val colors = MaterialTheme.colorScheme
    val container = if (isSystemInDarkTheme()) colors.surface else Color.White
    val vm : HomeViewModel = viewModel()
    val focusManager = LocalFocusManager.current
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false
    )
    val scope = rememberCoroutineScope()
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
            .padding(top = 50.dp)
            .padding(horizontal = 15.dp),
    ) {
        // HEADER
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Hola",
                        color = colors.onSurfaceVariant,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "Christian Moreno",
                        color = colors.onSurface,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                Box(
                    modifier = Modifier
                        .size(35.dp)
                        .clip(CircleShape)
                        .background(
                            colors.primary.copy(alpha = 0.2f)
                        ),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = "C",
                        color = colors.primary,
                        fontWeight = FontWeight.Bold
                    )
                }
                IconButton(
                    onClick = {}
                ){
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Logout,
                        contentDescription = "Cerrar Sesión",
                        tint = colors.primary,
                        modifier = Modifier
                            .size(35.dp)
                            .clip(CircleShape)
                            .background(
                            colors.primary.copy(alpha = 0.2f)
                        )
                            .padding(5.dp),

                    )
                }
            }
        }

        // GENERATE RECIPE
        item {
            Spacer(Modifier.height(15.dp))
            Text(
                text = "Crea, cocina, comparte y disfruta",
                color = colors.onSurface,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(Modifier.height(15.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = vm.ingredients,
                onValueChange = { vm.ingredients = it },
                shape = CircleShape,
                singleLine = true,
                placeholder = { Text("Escribe tur ingredientes...") },
                trailingIcon = {
                    IconButton(
                        onClick = {
                            hideKeyboard(
                                focusManager = focusManager
                            )
                            vm.generateRecipe(){
                                scope.launch {
                                    sheetState.partialExpand()
                                }
                            }
                        }
                    ){
                        Icon(
                            imageVector = Icons.Default.AutoAwesome,
                            contentDescription = "Gnerar Receta",
                            tint = Color.White,
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(colors.primary)
                                .padding(5.dp),
                        )
                    }
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = container,
                    unfocusedContainerColor = container,
                    disabledContainerColor = container,
                    errorContainerColor = container,
                    focusedBorderColor = colors.primary,
                    unfocusedBorderColor = colors.primary.copy(alpha = 0.6f),
                    cursorColor = colors.primary,
                    focusedTextColor = colors.onSurface,
                    unfocusedTextColor = colors.onSurface,
                    focusedPlaceholderColor = colors.onSurfaceVariant,
                    unfocusedPlaceholderColor = colors.onSurfaceVariant
                )

            )

            Spacer(Modifier.height(15.dp))
        }

        // Tus recetas recientes
        item {
            Text(
                text = "Tus recetas recientes",
                color = colors.onSurface,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(Modifier.height(15.dp))
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(vm.recentRecipes){ recipe ->
                    RecipeCard(
                        recipe = recipe,
                        onClick = {
                            scope.launch {
                                val recipePreview = RecipePreview(
                                    title = recipe.title,
                                    category = recipe.category,
                                    imageUrl = recipe.imageUrl,
                                    ingredients = recipe.ingredients,
                                    instructions = recipe.instructions,
                                    minutes = recipe.minutes,
                                    stars = recipe.stars,
                                    prompt = ""
                                )
                                vm.showModalFromList(
                                    recipe = recipePreview
                                )
                                sheetState.partialExpand()
                            }
                        }
                    )
                }
            }
            Spacer(Modifier.height(15.dp))
        }

        // Ideas Rapidas
        item {
            val tags = listOf(
                "Rapidas (10 min)",
                "Pocas calorias",
                "Sin horno",
                "Desayunos"
            )
            Text(
                text = "Ideas rápidas",
                color = colors.onSurface,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(Modifier.height(10.dp))
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(tags){ tag ->
                    Text(
                        text = tag,
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(colors.primary.copy(alpha = 0.1f))
                            .padding(horizontal = 20.dp, vertical = 10.dp),
                        color = colors.primary,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        // No sabes que cocinar hoy?
        item {
            Row(
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .fillMaxWidth()
                    .height(80.dp)
                    .clip(RoundedCornerShape(25.dp))
                    .background(colors.primary.copy(alpha = 0.1f))
                    .padding(15.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "¿No sabes que cocinar hoy?",
                        color = colors.onSurface,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "Genera una receta aleatoria",
                        color = colors.onSurfaceVariant,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Icon(
                    imageVector = Icons.Default.AutoAwesome,
                    contentDescription = "Generar Receta",
                    tint = colors.primary,
                    modifier = Modifier
                        .size(30.dp)
                )
            }
        }

        // Todas tus recetas
        item {
            Text(
                text = "Todas tus recetas",
                color = colors.onSurface,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(Modifier.height(15.dp))
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                val list = vm.recipes // o vm.Recipes si ese es el nombre real
                list.forEach { recipe ->
                    RecipeListItem(
                        recipe = recipe,
                        onClick = {
                            scope.launch {
                                val recipePreview = RecipePreview(
                                    title = recipe.title,
                                    category = recipe.category,
                                    imageUrl = recipe.imageUrl,
                                    ingredients = recipe.ingredients,
                                    instructions = recipe.instructions,
                                    minutes = recipe.minutes,
                                    stars = recipe.stars,
                                    prompt = ""
                                )
                                vm.showModalFromList(recipe = recipePreview)
                                sheetState.partialExpand()
                            }
                        }
                    )
                    Spacer(Modifier.height(10.dp))
                }
            }
        }
    }
    if (vm.isLoading){
        LoadingOverlay()
    }
    if (vm.showSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                scope.launch {
                    vm.showSheet = false
                    sheetState.hide()
                }
            },
            sheetState = sheetState,
        ){
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp)
                    .verticalScroll(rememberScrollState())
            ){
                AsyncImage(
                    model = vm.generatedRecipe?.imageUrl,
                    contentDescription = vm.generatedRecipe?.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                        .clip(RoundedCornerShape(26.dp)),
                    contentScale = ContentScale.Crop
                )
                Spacer(Modifier.height(16.dp))
                Text(
                    text =vm.generatedRecipe?.title ?: "Sin Titulo",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = colors.onSurface,
                )
                Spacer(Modifier.height(16.dp))
                Row (
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(colors.primary.copy(alpha = 0.15f))
                        .padding(horizontal = 16.dp, vertical = 10.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ){
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = colors.primary
                    )
                    Text(
                        text = "${vm.generatedRecipe?.stars}",
                        color = colors.primary,
                        fontWeight = FontWeight.Bold
                    )
                    Icon(
                        imageVector = Icons.Default.Schedule,
                        contentDescription = null,
                        tint = colors.primary
                    )
                    Text(
                        text = "${vm.generatedRecipe?.minutes} min",
                        color = colors.primary
                    )
                    Text(
                        text = "${vm.generatedRecipe?.category}",
                        color = colors.primary,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(Modifier.height(16.dp))
                Text(
                    text = "Ingredientes",
                    color = colors.onSurface,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(16.dp))
                FlowRow (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ){
                    val ingredients = vm.generatedRecipe?.ingredients ?: listOf()
                    ingredients.forEach { ingredient ->
                        Text(
                            text = ingredient,
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(colors.primary.copy(alpha = 0.1f))
                                .padding(horizontal = 20.dp, vertical = 10.dp),
                            color = colors.primary
                        )
                    }
                }
                Spacer(Modifier.height(16.dp))
                Text(
                    text = "Preparación",
                    color = colors.onSurface,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(16.dp))
                val instructions = vm.generatedRecipe?.instructions ?: listOf()
                instructions.forEachIndexed { index, instruction ->
                    Row {
                        Text(
                            text = "${index + 1}.",
                            color = colors.primary,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = instruction,
                            color = colors.onSurface
                        )
                    }
                }
                // Boton cerrar
                Spacer(Modifier.height(30.dp))
                Button(
                    onClick = {
                        scope.launch {
                            vm.hideModal()
                        }
                    },
                    modifier = Modifier
                        .padding()
                        .clip(CircleShape)
                        .background(colors.primary)
                        .align(Alignment.End),
                ){
                    Text(
                        text = "Cerrar",
                        color = colors.onPrimary,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview(){
    RecipeTheme {
        HomeScreen()
    }
}