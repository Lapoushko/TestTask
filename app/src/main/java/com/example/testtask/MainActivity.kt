package com.example.testtask

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.testtask.controller.PictureDataControllerImpl
import com.example.testtask.databinding.ActivityMainBinding
import com.example.testtask.service.SerperServiceImpl

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var serperService: SerperServiceImpl
    private lateinit var pictureDataController: PictureDataControllerImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initServicesAndControllers()
        setupListeners()
        setupWindowInsets()
    }

    /**
     * Инициализация сервисов и контроллеров
     */
    private fun initServicesAndControllers() {
        serperService = SerperServiceImpl()
        pictureDataController = PictureDataControllerImpl(serperService, binding.recyclerView)
        pictureDataController.setupRecyclerView()
    }

    /**
     * Кнопка поиска
     */
    private fun setupListeners() {
        binding.searchButton.setOnClickListener {
            val query = binding.search.text.toString()
            serperService.searchImagesByQuery(query)
            pictureDataController.updatePictures(serperService.getPictures())
        }
    }

    private fun setupWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

}