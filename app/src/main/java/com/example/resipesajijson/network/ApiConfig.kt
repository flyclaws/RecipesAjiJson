package com.example.resipesajijson.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    companion object {
        fun getApiService(): ApiService {
            // Interceptor untuk logging, berguna untuk debugging.
            // Menampilkan log dari request dan response di Logcat.
            val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

            // Membuat client OkHttp dan menambahkan interceptor
            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()

            // Membuat instance Retrofit
            val retrofit = Retrofit.Builder()
                // DIperbaiki: baseUrl sekarang hanya berisi domain utama dan diakhiri dengan /
                .baseUrl("https://dummyjson.com/")
                // Menambahkan converter factory untuk (de)serialisasi objek JSON menggunakan Gson.
                .addConverterFactory(GsonConverterFactory.create())
                // Menggunakan client OkHttp yang sudah dikonfigurasi.
                .client(client)
                .build()

            // Membuat implementasi dari ApiService interface.
            return retrofit.create(ApiService::class.java)
        }
    }
}