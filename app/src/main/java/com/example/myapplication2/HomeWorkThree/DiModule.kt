package com.example.myapplication2.HomeWorkThree

object DiModule {
    private var imageLoader: ImageLoader? = null

    fun provideImageLoader(): ImageLoader {
        return imageLoader ?: CoilImageLoader().also { imageLoader = it }
    }
}