package com.example.testtask.controller

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testtask.activity.MainActivity
import com.example.testtask.component.RecyclerViewAdapter
import com.example.testtask.model.Picture
import com.example.testtask.service.SerperServiceImpl

/**
 * Реализует интерфейс RecyclerDataController
 */
class PictureDataControllerImpl(private val serperService: SerperServiceImpl,
    private val recyclerView: RecyclerView,
    private val activity: MainActivity) : PictureDataController {

    private lateinit var pictures: MutableList<Picture>

    override fun setupRecyclerView() {
        this.recyclerView.setHasFixedSize(true)
        this.recyclerView.layoutManager = GridLayoutManager(recyclerView.context,1)
        pictures = serperService.getPictures()
        this.recyclerView.adapter = RecyclerViewAdapter(pictures, activity)
    }

    override fun updatePictures(pictures: MutableList<Picture>) {
        this.pictures = pictures
        if (this.recyclerView.adapter is RecyclerViewAdapter){
            (this.recyclerView.adapter as RecyclerViewAdapter).notifyDataSetChanged()
        }
        else{
            this.recyclerView.adapter = RecyclerViewAdapter(pictures, activity)
        }
    }
}