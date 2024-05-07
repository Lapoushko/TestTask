package com.example.testtask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.testtask.databinding.FragmentPicturesBinding
import com.example.testtask.model.Picture
import com.example.testtask.util.LoaderPicture


/**
 *  Фрагмент изображения
 */
class PicturesFragment : Fragment() {

    private lateinit var binding: FragmentPicturesBinding
    private lateinit var picture: Picture
    private lateinit var loaderPicture: LoaderPicture

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPicturesBinding.inflate(layoutInflater, container, false)
        loaderPicture = LoaderPicture()
        loaderPicture.loadImage(picture.image, binding.pictureView)
        loaderPicture.loadImage(picture.image, binding.actionImage)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(picture: Picture) = PicturesFragment().apply {
            this.picture = picture
        }
    }
}