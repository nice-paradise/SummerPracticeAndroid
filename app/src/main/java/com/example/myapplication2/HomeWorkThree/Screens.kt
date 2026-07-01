package com.example.myapplication2.HomeWorkThree

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun GenerationScreen(viewModel: FilmsViewModel, onNavigateToList: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = viewModel.inputCount,
            onValueChange = { viewModel.onInputCountChange(it) },
            label = { Text("Количество фильмов") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { viewModel.generateFilms() },
            enabled = viewModel.isValidNumber,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Сгенерировать")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = onNavigateToList,
            enabled = viewModel.isGenerated,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Открыть список")
        }
    }
}

@Composable
fun ListScreen(
    viewModel: FilmsViewModel,
    imageLoader: ImageLoader, // Внедряем через конструктор экрана
    onBack: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Button(onClick = onBack, modifier = Modifier.padding(bottom = 8.dp)) {
            Text("Назад")
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            TextField(
                value = viewModel.filterQuery,
                onValueChange = { viewModel.onFilterQueryChange(it) },
                label = { Text("Год (<= чем)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { viewModel.applyFilter() }) {
                Text("Фильтр")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (viewModel.filteredFilms.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Фильмы не найдены")
            }
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(viewModel.filteredFilms) { film ->
                    FilmItem(
                        film = film,
                        imageLoader = imageLoader // Передаем ниже по графу
                    )
                }
            }
        }
    }
}

@Composable
fun FilmItem(
    film: FilmModel,
    imageLoader: ImageLoader // Использование абстракции вместо Coil
) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
            imageLoader.LoadImage(
                url = film.posterUrl,
                contentDescription = null,
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = film.name, style = MaterialTheme.typography.titleMedium)
                Text(text = "Год: ${film.releaseDate}", style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}