package com.br.testeame

import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.util.*

class UtilTest {

    @Test
    fun returnMounthOctober() {

        val value = "Outubro"
        val october = Util.returnMount(10)
        assertEquals(value,october)
    }

    @Test
    fun returnMounthJanuary() {

        val value = "Janeiro"
        val january = Util.returnMount(1)
        assertEquals(value,january)
    }

    @Test
    fun returnMoney() {

        var value : Float = 10F
        val valueReturn = "R$ 10,00"
        val money = Util.stringMoney(value)
        assertEquals(valueReturn,money)
    }

    @Test
    fun returnInvalidMount() {

        val value = "mes invalido"
        assertEquals(value, Util.returnMount(13))
    }

    @Test
    fun testDataNow() {

        val date = Calendar.getInstance()

        val  monthParam = Util.returnMount(date[Calendar.MONTH])

        var dayParam = date[Calendar.DAY_OF_MONTH]

        var yearParam = date[Calendar.YEAR]

        val editData = "$dayParam de $monthParam de $yearParam"

        assertEquals(editData, Util.dataNow())
    }


}