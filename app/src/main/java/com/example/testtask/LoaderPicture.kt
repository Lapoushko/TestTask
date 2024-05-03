package com.example.testtask

import android.widget.ImageView
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
            .into(image)
    }
}