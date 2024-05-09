package com.example.testtask.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.testtask.component.ViewPageAdapter
import com.example.testtask.databinding.FragmentPicturesBinding
import com.example.testtask.model.Picture
import com.example.testtask.util.LoaderPicture


/**
 *  Фрагмент изображения
 */
class PicturesFragment : Fragment() {

    private lateinit var binding: FragmentPicturesBinding
    private lateinit var pictures: List<Picture>
    private lateinit var viewPager: ViewPager2
    private lateinit var loaderPicture: LoaderPicture
    private var position: Int = 0
    private lateinit var pathWeb: String
    private val callback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            pathWeb = pictures[position].pathWeb
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentPicturesBinding.inflate(layoutInflater, container, false)
        loaderPicture = LoaderPicture()

        binding.buttonLink.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(pathWeb))
            startActivity(intent)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPager = binding.viewPagerPictures
        viewPager.adapter = ViewPageAdapter(pictures)

        viewPager.setCurrentItem(position, false)
        viewPager.registerOnPageChangeCallback(callback)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewPager.unregisterOnPageChangeCallback(callback)
    }
    companion object {
        @JvmStatic
        fun newInstance(pictures: List<Picture>, position: Int) = PicturesFragment().apply {
            this.pictures = pictures
            this.position = position
            pathWeb = pictures[position].pathWeb
        }
    }
}