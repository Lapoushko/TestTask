package com.example.testtask.service

/**
 * Сервис поиска изображений в Google
 */
interface SerperService {
    /**
     * Получить изображение по запросу
     * @param query Запрос
     * @param page Страница
     */
    fun searchImagesByQuery(query: String, page: Int)
}