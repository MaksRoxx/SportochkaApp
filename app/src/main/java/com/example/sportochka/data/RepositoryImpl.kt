package com.example.sportochka.data

import com.example.sportochka.data.AppDao.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RepositoryImpl(private val appDao: AppDao) : Repository {

    fun okhttp(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        return client
    }

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL).client(okhttp())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(AppDao::class.java)

}