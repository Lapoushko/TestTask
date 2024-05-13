package com.example.testtask.service

import com.example.testtask.model.Picture
import com.example.testtask.repository.PictureRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.json.JSONObject

/**
 * Константа API ключа
 */
const val API_KEY = ""
/**
 * Сервис, который находит нужные изображения в Google
 * @param client Клиент для HTTP вызовов
 * @param pictureRepository репозиторий изображений
 */
class SerperServiceImpl(private val client: OkHttpClient = OkHttpClient(),
                        private val pictureRepository: PictureRepository)
    : SerperService {
    private val mediaType = MediaType.parse("application/json")
    override fun searchImagesByQuery(query: String, page: Int) {
        if (query.isNotEmpty()) {
            CoroutineScope(Dispatchers.IO).launch {
                val body = RequestBody.create(mediaType, "{\"q\":\"${query}\",\"page\":${page}}")

                val request = Request.Builder()
                    .url("https://google.serper.dev/images")
                    .method("POST", body)
                    .addHeader("X-API-KEY", API_KEY)
                    .addHeader("Content-Type", "application/json")
                    .build()

                val response = client.newCall(request).execute()
                val jsonData: String? = response.body()?.string()

                val jsonObject = JSONObject(jsonData.toString())
                val jsonArray = jsonObject.getJSONArray("images")

                for (i in 0 until jsonArray.length()) {
                    val domain = jsonArray.getJSONObject(i).get("googleUrl").toString()
                    val imageUrl = jsonArray.getJSONObject(i).get("imageUrl").toString()
                    pictureRepository.addPicture(Picture(domain, imageUrl))
                }
            }
        }
    }
}