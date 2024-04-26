package com.kom.foodapp.utils

import java.text.NumberFormat
import java.util.Locale

fun Double?.doubleToCurrency(
    language: String,
    country: String,
): String? {
    return try {
        val localeID = Locale(language, country)
        val numberFormat = NumberFormat.getCurrencyInstance(localeID)
        numberFormat.maximumFractionDigits = 0
        numberFormat.format(this).toString()
    } catch (e: Exception) {
        null
    }
}

fun Double?.formatToRupiah() = this.doubleToCurrency("in", "ID")
