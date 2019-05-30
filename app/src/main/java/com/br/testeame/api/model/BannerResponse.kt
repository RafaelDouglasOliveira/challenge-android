package com.br.testeame.api.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class BannerResponse(
        @SerializedName("data")
        var bannerResponse: ArrayList<Banner>
)
