package com.example.testtask.controller

import com.example.testtask.model.Picture

/**
 * Интерфейс контроллера RecyclerData
 */
interface PictureDataController {
    /**
     * Установить recyclerView
     */
    public fun setupRecyclerView()

    /**
     * Обновить коллекцию изображений
     * @param pictures
     */
    public fun updatePictures(pictures: MutableList<Picture>)
}