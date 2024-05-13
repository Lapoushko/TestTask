package com.example.testtask.repository

import com.example.testtask.model.Picture

/**
 * Репозиторий изображений
 */
interface PictureRepository {
    /**
     * Добавить изображения в список
     * @param picture Изображение
     */
    fun addPicture(picture: Picture)

    /**
     * Очистить список изображений
     */
    fun deletePictures()

    /**
     * Получить все изображения
     * @return Список изображений
     */
    fun getPictures() : MutableList<Picture>
}