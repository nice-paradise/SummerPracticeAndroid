package com.example.myapplication2.HomeWork2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.imageLoader
import coil.request.ImageRequest
import kotlin.random.Random

class HomeWork2Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GuessTheNumberGame()
                }
            }
        }
    }
}

@Composable
fun GuessTheNumberGame() {
    val context = LocalContext.current
    val imageUrl = "https://img.magnific.com/premium-vector/emoticon-doing-thumb-up_24381-2032.jpg?semt=ais_hybrid&w=740&q=80"

    // Предварительная загрузка изображения сразу при запуске
    LaunchedEffect(Unit) {
        val request = ImageRequest.Builder(context)
            .data(imageUrl)
            .build()
        context.imageLoader.enqueue(request)
    }

    // Генерируем целевое число один раз и запоминаем его
    val targetNumber = remember { Random.nextInt(0, 101) }

    var userInput by remember { mutableStateOf("") }
    var feedbackMessage by remember { mutableStateOf("Угадайте число от 0 до 100") }
    var isGuessedCorrectly by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (isGuessedCorrectly) {
            // Отображаем изображение при правильном ответе
            AsyncImage(
                model = imageUrl,
                contentDescription = "Изображение успеха",
                modifier = Modifier.size(250.dp) // Немного увеличил размер изображения
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Поздравляем!\nВы угадали число $targetNumber!",
                style = MaterialTheme.typography.headlineLarge, // Сделал текст еще крупнее
                textAlign = TextAlign.Center,
                lineHeight = MaterialTheme.typography.headlineLarge.lineHeight * 1.2
            )
        } else {
            // Отображаем текст подсказки
            Text(
                text = feedbackMessage,
                style = MaterialTheme.typography.headlineMedium, // Сделал текст подсказки крупнее
                modifier = Modifier.padding(bottom = 32.dp),
                textAlign = TextAlign.Center
            )

            // Поле для ввода текста
            TextField(
                value = userInput,
                onValueChange = { userInput = it },
                label = { Text("Введите число") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.padding(bottom = 16.dp),
                singleLine = true
            )

            // Кнопка для проверки догадки
            Button(
                onClick = {
                    val enteredNumber = userInput.toIntOrNull()
                    if (enteredNumber != null) {
                        when {
                            enteredNumber < targetNumber -> {
                                feedbackMessage = "Введенное число меньше загаданного."
                            }
                            enteredNumber > targetNumber -> {
                                feedbackMessage = "Введенное число больше загаданного."
                            }
                            else -> {
                                isGuessedCorrectly = true
                            }
                        }
                    } else {
                        feedbackMessage = "Пожалуйста, введите корректное число."
                    }
                }
            ) {
                Text("Угадать", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}
