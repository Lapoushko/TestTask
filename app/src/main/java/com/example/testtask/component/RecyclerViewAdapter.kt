package com.example.testtask.component

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.testtask.LoaderPicture
import com.example.testtask.R
import com.example.testtask.model.Picture

/**
 * Адаптер для отображения данных
 */
class RecyclerViewAdapter(private val pictures: List<Picture>) :
    RecyclerView.Adapter<RecyclerViewAdapter.RouteViewHolder>() {
    class RouteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imageView: ImageView = itemView.findViewById(R.id.picture_view)
    }
    private val loaderPicture = LoaderPicture()

    /**
     * Установка адаптера при создании
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RouteViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(
                R.layout.recycler_item_view, parent, false
            )
        return RouteViewHolder(view)
    }

    /**
     * Количество элементов
     */
    override fun getItemCount(): Int {
        return pictures.size
    }

    /**
     * Отображение данных в определённой позиции
     */
    override fun onBindViewHolder(holder: RouteViewHolder, position: Int) {
        if (itemCount > 0){
            val picture = pictures[position]
            loaderPicture.loadImage(picture.image, holder.imageView)
        }
    }

}
