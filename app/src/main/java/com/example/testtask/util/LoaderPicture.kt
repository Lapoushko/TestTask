package com.example.testtask.util

import android.widget.ImageView
import com.example.testtask.PicturesFragment
import com.example.testtask.model.Picture
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

    companion object {
        @JvmStatic
        fun newInstance(picture: Picture) = PicturesFragment()
    }
}