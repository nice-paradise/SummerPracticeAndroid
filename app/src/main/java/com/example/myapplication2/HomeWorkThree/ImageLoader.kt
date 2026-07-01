package com.example.myapplication2.HomeWorkThree


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

interface ImageLoader {
    @Composable
    fun LoadImage(
        url: String,
        contentDescription: String?,
        modifier: Modifier
    )
}