package com.example.testtask.component

import com.example.testtask.model.Picture

/**
 * Обработчик нажатий
 */
interface OnPictureClickListener {
    /**
     * Нажатие на изображение
     * @param pictures Список изображений
     * @param pos Текущая позиция изображения
     */
    fun onPictureClick(pictures: List<Picture>, pos: Int)
}