package com.br.testeame.api.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Product(

        @SerializedName("id")
        var id: Int,

        @SerializedName("nome")
        var name: String,

        @SerializedName("urlImagem")
        var urlImage: String,

        @SerializedName("descricao")
        var description: String,

        @SerializedName("precoDe")
        var priceOf: Float,

        @SerializedName("precoPor")
        var priceOff: Float,

        @SerializedName("categoria")
        var category: Category

) : Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readInt(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readFloat(),
                parcel.readFloat(),
                parcel.readParcelable(Category::class.java.classLoader))


        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeInt(id)
                parcel.writeString(name)
                parcel.writeString(urlImage)
                parcel.writeString(description)
                parcel.writeFloat(priceOf)
                parcel.writeFloat(priceOff)
                parcel.writeParcelable(category, flags)
        }

        override fun describeContents(): Int {
                return 0
        }

        companion object CREATOR : Parcelable.Creator<Product> {
                override fun createFromParcel(parcel: Parcel): Product {
                        return Product(parcel)
                }

                override fun newArray(size: Int): Array<Product?> {
                        return arrayOfNulls(size)
                }
        }
}
