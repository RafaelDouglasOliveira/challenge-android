package com.br.testeame

import android.content.Context
import android.net.ConnectivityManager
import java.text.NumberFormat
import java.util.*

class Util {
    companion object {

        fun isConected(context: Context): Boolean {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            if (cm != null) {
                val ni = cm.activeNetworkInfo

                return ni != null && ni.isConnected
            }

            return false
        }

        fun stringMoney(number : Float) : String{

            val locale = Locale("pt", "BR")

            val format = NumberFormat.getCurrencyInstance(locale)
            return format.format(number)
        }

        fun dataNow( ): String {
            val date = Calendar.getInstance()

            val  monthParam = returnMount(date[Calendar.MONTH])

            var dayParam = date[Calendar.DAY_OF_MONTH]

            var yearParam = date[Calendar.YEAR]

            return "$dayParam de $monthParam de $yearParam"
        }

        fun returnMount(value : Int) : String{

            return when(value) {
                1 -> "Janeiro"
                2 -> "Fevereiro"
                3 -> "Março"
                4 -> "Abril"
                5 -> "Maio"
                6 -> "Junho"
                7 -> "Julho"
                8 -> "Agosto"
                9 -> "Setembro"
                12 -> "Dezembro"
                11 -> "Novembro"
                10 -> "Outubro"
                else -> "mes invalido"
            }
        }

    }
}