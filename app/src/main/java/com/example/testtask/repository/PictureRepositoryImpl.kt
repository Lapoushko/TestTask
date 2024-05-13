package com.example.testtask.repository

import com.example.testtask.model.Picture

/**
 * Работа с изображениями
 */
class PictureRepositoryImpl:PictureRepository {
    /**
     * Список всех изображений
     */
    private val pictures = mutableListOf<Picture>()

    override fun addPicture(picture: Picture) {
        pictures.add(picture)
    }

    override fun deletePictures() {
        pictures.clear()
    }

    override fun getPictures(): MutableList<Picture> {
        return pictures
    }

}