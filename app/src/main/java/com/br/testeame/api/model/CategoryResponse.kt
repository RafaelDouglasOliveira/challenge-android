package com.br.testeame.api.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class CategoryResponse(
        @SerializedName("data")
        val categoryResponse: ArrayList<Category>
)
