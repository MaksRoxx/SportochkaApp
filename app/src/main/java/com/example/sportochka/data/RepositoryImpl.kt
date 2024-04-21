package com.example.sportochka.data

import com.example.sportochka.data.AppDao.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RepositoryImpl() {

    //val basicAuth = okhttp3.Credentials.basic(basic.name, basic.password)
    // basic
    fun okhttpv1(basic: data_login): OkHttpClient {
        val client = OkHttpClient.Builder()
            .addInterceptor(BasicAuthInterceptor(basic.name, basic.password))
            .build()

        return client
    }

    // barier
    fun create(token: String): AppDao {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request: Request = chain.request().newBuilder()
                    .addHeader("Authorization", token)
                    .build()
                chain.proceed(request)
            }
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(AppDao::class.java)
    }


    fun inst(basic: data_login) {
        Retrofit.Builder()
            .baseUrl(BASE_URL).client(okhttpv1(basic))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AppDao::class.java)
    }
}