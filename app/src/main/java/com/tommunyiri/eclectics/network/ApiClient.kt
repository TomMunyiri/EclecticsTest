package com.tommunyiri.eclectics.network

import com.tommunyiri.eclectics.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    companion object{

        fun getRetrofit(): Retrofit? {
            var retrofit: Retrofit? = null
            if (retrofit == null) {
                //val interceptor=TokenInterceptor();

                val client = OkHttpClient.Builder()
                        .build();
                retrofit = Retrofit.Builder()
                        .baseUrl(Constants.BASE_URL)
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
            }
            return retrofit
        }
    }
}