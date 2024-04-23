package com.example.sportochka.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportochka.data.AppDao
import com.example.sportochka.data.BasicAuthInterceptor
import com.example.sportochka.data.RepositoryImpl
import com.example.sportochka.data.data_login
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RegisterViewModel() : ViewModel() {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

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
            .baseUrl(AppDao.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(AppDao::class.java)
    }


    fun inst(basic: data_login) {
        val retrofit = Retrofit.Builder()
            .baseUrl(AppDao.BASE_URL).client(okhttpv1(basic))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val appDao =  retrofit.create(AppDao::class.java)

        //viewModelScope.launch {
          //  appDao.register("wgediuewgeuwg")
        //}
        appDao.login("wugefiuewge").enqueue(object: Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                response.code()
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                //t
            }
        })
    }

    // Функция для регистрации с обычной аутентификацией
    fun registerWithBasicAuth(username: String, password: String) {
        coroutineScope.launch {
            try {
                Log.d("kkk", "${inst(data_login(username, password))}")
                inst(data_login(username, password))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}