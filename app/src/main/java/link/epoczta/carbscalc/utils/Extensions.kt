package link.epoczta.carbscalc.utils

import java.text.DecimalFormatSymbols

fun String?.toDoubleOrZero(): Double {
    val localSeparator = DecimalFormatSymbols.getInstance().decimalSeparator.toString()
    return try {
        this?.replace(localSeparator, ".")?.toDouble() ?: 0.0
    } catch (e: NumberFormatException) {
        0.0
    }
}