package com.br.testeame.api.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Category(

        @SerializedName("id")
        val id : Int,

        @SerializedName("descricao")
        val description : String,

        @SerializedName("urlImagem")
        val urlImage : String
): Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readInt(),
                parcel.readString(),
                parcel.readString())


        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeInt(id)
                parcel.writeString(description)
                parcel.writeString(urlImage)
        }

        override fun describeContents(): Int {
                return 0
        }

        companion object CREATOR : Parcelable.Creator<Category> {
                override fun createFromParcel(parcel: Parcel): Category {
                        return Category(parcel)
                }

                override fun newArray(size: Int): Array<Category?> {
                        return arrayOfNulls(size)
                }
        }
}
