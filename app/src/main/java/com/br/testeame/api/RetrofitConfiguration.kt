package com.br.testeame.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitConfiguration {


    companion object {

        private const val url = "https://alodjinha.herokuapp.com/"

        fun getRetrofitInstance() : Retrofit {
            return Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
    }
}