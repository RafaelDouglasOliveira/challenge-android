package com.br.testeame.api

import com.br.testeame.api.model.*
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface Endpoint {

    @GET("banner")
    fun getBanner(): Observable<BannerResponse>

    @GET("categoria")
    fun getCategory() : Observable<CategoryResponse>

    @GET("produto")
    fun getProducts() : Observable<ProductsResponse>

    @GET("produto/maisvendidos")
    fun getTopSellingProducts() : Observable<ProductsResponse>

    @GET("produto")
    fun getCategoryProducts(@Query("categoriaId") categoriaId : Int) :
                             Observable<ProductsResponse>

    @GET("produto/{produtoId}")
    fun getProduct(@Path("produtoId") product : Int) : Observable<Product>

    @POST("produto/{produtoId}")
    fun postProduct(@Path("produtoId") product: Int) : Observable<ProductResponse>


}