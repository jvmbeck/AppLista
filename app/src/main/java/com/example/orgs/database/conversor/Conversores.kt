package com.example.orgs.database.conversor

import androidx.room.TypeConverter
import java.math.BigDecimal

class Conversores {

    @TypeConverter
    fun deDouble(valor: Double?): BigDecimal{
        return valor?.let { BigDecimal(valor.toString()) } ?: BigDecimal.ZERO
    }
    @TypeConverter
    fun deBigDecimalParaDouble(valor: BigDecimal?) : Double?{
        return valor?.let { valor.toDouble() }
    }
}