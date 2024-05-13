package com.example.testtask.component

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.testtask.R
import com.example.testtask.model.Picture
import com.example.testtask.util.LoaderPicture

/**
 * Адаптер для отображения данных
 * @param pictures Список изображений
 * @param listener Слушатель нажатия на изображения
 */
class RecyclerViewAdapter(private val pictures: List<Picture>,
                          private val listener: OnPictureClickListener) :
    RecyclerView.Adapter<RecyclerViewAdapter.PictureViewHolder>() {
    class PictureViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imageView: ImageView = itemView.findViewById(R.id.picture_view)
    }
    private val loaderPicture = LoaderPicture()

    /**
     * Установка адаптера при создании
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(
                R.layout.recycler_item_view, parent, false
            )
        return PictureViewHolder(view)
    }

    /**
     * Количество элементов
     * @return получить количество изображений
     */
    override fun getItemCount(): Int {
        return pictures.size
    }

    /**
     * Отображение данных в определённой позиции
     */
    override fun onBindViewHolder(holder: PictureViewHolder, position: Int) {
        val picture = pictures[position]
        loaderPicture.loadImage(picture.image, holder.imageView)
        holder.imageView.setOnClickListener {
            listener.onPictureClick(pictures, position)
        }
    }

}
