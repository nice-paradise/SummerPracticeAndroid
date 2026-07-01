package com.example.myapplication2.HomeWorkThree

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

class MainActivity : ComponentActivity() {
    private val viewModel: FilmsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Внедрение зависимости на уровне Activity (Composition Root)
        val imageLoader = DiModule.provideImageLoader()

        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    AppNavigation(viewModel = viewModel, imageLoader = imageLoader)
                }
            }
        }
    }
}

@Composable
fun AppNavigation(viewModel: FilmsViewModel, imageLoader: ImageLoader) {
    var currentScreen by remember { mutableStateOf("GenerationScreen") }

    when (currentScreen) {
        "GenerationScreen" -> GenerationScreen(
            viewModel = viewModel,
            onNavigateToList = { currentScreen = "ListScreen" }
        )
        "ListScreen" -> ListScreen(
            viewModel = viewModel,
            imageLoader = imageLoader, // Прокидываем зависимость в ListScreen
            onBack = { currentScreen = "GenerationScreen" }
        )
    }
}