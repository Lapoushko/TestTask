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
 * Адаптер для view page
 */
class ViewPageAdapter(private val pictures: List<Picture>) :
    RecyclerView.Adapter<ViewPageAdapter.PageViewHolder>() {
    class PageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val actionImage: ImageView = itemView.findViewById(R.id.action_image)
    }

    private val loaderPicture = LoaderPicture()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PageViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(
                R.layout.fragment_pictures, parent, false
            )
        return PageViewHolder(view)
    }

    override fun onBindViewHolder(holder: PageViewHolder, position: Int) {
        val picture = pictures[position]
        loaderPicture.loadImage(picture.image, holder.actionImage)
    }

    override fun getItemCount(): Int  = pictures.size
}