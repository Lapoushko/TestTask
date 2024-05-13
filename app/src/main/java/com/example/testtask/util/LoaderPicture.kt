package com.example.testtask.util

import android.widget.ImageView
import com.squareup.picasso.Picasso

/**
 * Класс загрузки изображений по uri
 */
class LoaderPicture {
    /**
     * загрузить изображение
     * @param path путь к изображению
     * @param image ImageView изображения
     */
    fun loadImage(path: String, image: ImageView){
        Picasso.get()
            .load(path)
            .into(image)
        Picasso.get().setIndicatorsEnabled(true)
    }
}