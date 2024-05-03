package com.example.testtask.controller

import com.example.testtask.model.Picture

/**
 * Интерфейс контроллера RecyclerData
 */
interface PictureDataController {
    /**
     * Установить recyclerView
     */
    fun setupRecyclerView()

    /**
     * Обновить коллекцию изображений
     * @param pictures
     */
    fun updatePictures(pictures: MutableList<Picture>)
}