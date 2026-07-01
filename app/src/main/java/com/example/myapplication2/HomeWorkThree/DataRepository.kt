package com.example.myapplication2.HomeWorkThree


import java.util.UUID
import java.util.Random

object DataRepository {
    private var filmsList: List<FilmModel> = emptyList()

    fun getFilms(): List<FilmModel> = filmsList

    fun generateRandomFilms(count: Int) {
        val names = listOf("Inception", "Interstellar", "Shutter Island", "The Matrix", "Pulp Fiction", "The Godfather", "Joker", "Snatch")
        val random = Random()
        filmsList = List(count) { index ->
            FilmModel(
                id = UUID.randomUUID().toString(),
                name = "${names[random.nextInt(names.size)]} ${index + 1}",
                posterUrl = "https://picsum.photos/seed/${random.nextInt(1000)}/200/300",
                description = "Description for film $index",
                releaseDate = 1990 + random.nextInt(35)
            )
        }
    }
}