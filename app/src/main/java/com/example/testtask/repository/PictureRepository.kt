package com.example.testtask.repository

import com.example.testtask.model.Picture

/**
 * Репозиторий изображений
 */
class PictureRepository {
    /**
     * Список изображений
     */
    var pictures = mutableListOf<Picture>()
        get() = field
        private set

    /**
     * Добавить изображения в список
     */
    fun addPicture(picture: Picture) = pictures.add(picture)

    /**
     * Очистить список изображений
     */
    fun clearPictures() = pictures.clear()
}