package com.example.testtask.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testtask.R
import com.example.testtask.component.OnPictureClickListener
import com.example.testtask.controller.PictureDataControllerImpl
import com.example.testtask.databinding.ActivityMainBinding
import com.example.testtask.model.Picture
import com.example.testtask.repository.PictureRepository
import com.example.testtask.repository.PictureRepositoryImpl
import com.example.testtask.service.SerperServiceImpl
import okhttp3.OkHttpClient

/**
 * Основная активность
 */
class MainActivity : AppCompatActivity(), OnPictureClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var serperService: SerperServiceImpl
    private lateinit var pictureDataController: PictureDataControllerImpl

    private val pictureRepository: PictureRepository = PictureRepositoryImpl()
    private var page = 1 //Номер страницы
    private var query: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initControllers()
        setupListeners()

        scrolling()
    }

    /**
     * Инициализация контроллера
     */
    private fun initControllers() {
        serperService = SerperServiceImpl(OkHttpClient(), pictureRepository)
        pictureDataController = PictureDataControllerImpl(
            binding.recyclerView,
            this,
            pictureRepository
        )
        pictureDataController.setupRecyclerView()
    }

    /**
     * Установка слушателя
     */
    private fun setupListeners() {
        binding.searchButton.setOnClickListener {
            if (binding.search.text.toString()!= query) {
                pictureRepository.deletePictures()
                page = 1
                query = binding.search.text.toString()
            }
            serperService.searchImagesByQuery(query, page)
            pictureDataController.updatePictures(pictureRepository.getPictures())
        }
    }

    override fun onPictureClick(pictures: List<Picture>, pos: Int) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.picture_holder, PicturesFragment.newInstance(pictures, pos))
            .addToBackStack(null)
            .commit()
    }

    /**
     * Пагинация списка
     */
    private fun scrolling(){
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val totalItemCount = layoutManager.itemCount
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()

                if (lastVisibleItemPosition + 10 >= totalItemCount) {
                    page++
                    serperService.searchImagesByQuery(query, page)
                    pictureDataController.updatePictures(pictureRepository.getPictures())
                }
            }
        })
    }
}