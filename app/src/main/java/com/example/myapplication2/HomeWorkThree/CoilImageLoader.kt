package com.example.myapplication2.HomeWorkThree

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage

class CoilImageLoader : ImageLoader {
    @Composable
    override fun LoadImage(
        url: String,
        contentDescription: String?,
        modifier: Modifier
    ) {
        AsyncImage(
            model = url,
            contentDescription = contentDescription,
            modifier = modifier
        )
    }
}