package com.example.testtask.service

/**
 * Сервис поиска изображений в Google
 */
interface SerperService {
    /**
     * Получить изображение по запросу
     */
    public fun searchImagesByQuery(query: String, page: Int)
}