package com.br.testeame.api

import com.br.testeame.api.model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface Endpoint {

    @GET("banner")
    fun getBanner(): Call<BannerResponse>

    @GET("categoria")
    fun getCategory() : Call<CategoryResponse>

    @GET("produto")
    fun getProducts() : Call<ProductsResponse>

    @GET("produto/maisvendidos")
    fun getTopSellingProducts() : Call<ProductsResponse>

    @GET("produto")
    fun getCategoryProducts(@Query("categoriaId") categoriaId : Int) :
                             Call<ProductsResponse>

    @GET("produto/{produtoId}")
    fun getProduct(@Path("produtoId") product : Int) : Call<Product>

    @POST("produto/{produtoId}")
    fun postProduct(@Path("produtoId") product: Int) : Call<ProductResponse>


}