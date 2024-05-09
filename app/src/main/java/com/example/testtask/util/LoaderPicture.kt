package com.example.testtask.util

import android.widget.ImageView
import com.example.testtask.R
import com.squareup.picasso.Picasso

/**
 * Класс загрузки изображений по uri
 */
class LoaderPicture {
    /**
     * загрузить изображение
     */
    fun loadImage(path: String, image: ImageView){
        Picasso.get()
            .load(path)
            .error(R.drawable.error)
            .into(image)
        Picasso.get().setIndicatorsEnabled(true)
    }
}