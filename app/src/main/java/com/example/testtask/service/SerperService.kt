package com.example.testtask.service

import com.example.testtask.model.Picture

/**
 * Сервис поиска изображений в Google
 */
interface SerperService {
    /**
     * Получить изображение по запросу
     */
    public fun searchImagesByQuery(query: String, page: Int)

    /**
     * Вернуть список изображений
     */
    public fun getPictures() : List<Picture>
}