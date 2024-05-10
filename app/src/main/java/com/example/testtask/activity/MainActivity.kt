package com.example.testtask.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.NestedScrollView
import com.example.testtask.R
import com.example.testtask.component.OnPictureClickListener
import com.example.testtask.controller.PictureDataControllerImpl
import com.example.testtask.databinding.ActivityMainBinding
import com.example.testtask.model.Picture
import com.example.testtask.service.SerperServiceImpl


class MainActivity : AppCompatActivity(), OnPictureClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var serperService: SerperServiceImpl
    private lateinit var pictureDataController: PictureDataControllerImpl

    private var page = 1
    private lateinit var query: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initServicesAndControllers()
        setupListeners()
        setupWindowInsets()

        binding.nestedScrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            // on scroll change we are checking when users scroll as bottom.
            if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                // in this method we are incrementing page number,
                // making progress bar visible and calling get data method.
                page++
                serperService.searchImagesByQuery(query, page)
                pictureDataController.updatePictures(serperService.getPictures())
            }
        })
    }

    /**
     * Инициализация сервисов и контроллеров
     */
    private fun initServicesAndControllers() {
        serperService = SerperServiceImpl()
        pictureDataController = PictureDataControllerImpl(serperService, binding.recyclerView, this)
        pictureDataController.setupRecyclerView()
    }

    /**
     * Кнопка поиска
     */
    private fun setupListeners() {
        binding.searchButton.setOnClickListener {
            query = binding.search.text.toString()
            serperService.searchImagesByQuery(query, page)
            pictureDataController.updatePictures(serperService.getPictures())
        }
    }

    /**
     * Установка верхнего бара
     */
    private fun setupWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    override fun onPictureClick(pictures: List<Picture>, pos: Int) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.picture_holder, PicturesFragment.newInstance(pictures, pos))
                .addToBackStack(null)
                .commit()
        }
}