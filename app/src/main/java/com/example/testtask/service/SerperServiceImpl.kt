package com.example.testtask.service

import com.example.testtask.model.Picture
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.json.JSONObject


const val API_KEY = "0d97c79c76e7c330822f377cfcd7e0e72454648d"
/**
 * Сервис, который находит нужные изображения в Google
 */
class SerperServiceImpl(private val client: OkHttpClient = OkHttpClient()) : SerperService {
    private val mediaType = MediaType.parse("application/json")
    private val pictures = mutableListOf<Picture>()
    override fun searchImagesByQuery(query: String, page: Int) {
        pictures.clear()
        if (query.isNotEmpty()) {
            CoroutineScope(Dispatchers.IO).launch {
                val body = RequestBody.create(mediaType, "{\"q\":\"${query}\",\"page\":${page}}")
                //"{\"q\":\"${query}\",\"page\":1}"

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
                    pictures.add(Picture(domain, imageUrl))
                }
            }
        }
    }

    override fun getPictures(): MutableList<Picture> {
        return pictures
    }
}