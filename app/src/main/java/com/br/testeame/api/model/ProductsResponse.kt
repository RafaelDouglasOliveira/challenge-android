package com.br.testeame.api.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class ProductsResponse(
        @SerializedName("data")
        val productsResponse: ArrayList<Product>

)


