package com.example.testtask.component

import com.example.testtask.model.Picture

/**
 * Обработчик нажатий
 */
interface OnPictureClickListener {
    /**
     * Нажатие на изображение
     */
    fun onPictureClick(picture: Picture)
}