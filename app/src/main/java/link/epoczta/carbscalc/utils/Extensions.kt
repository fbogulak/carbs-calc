package link.epoczta.carbscalc.utils

fun String?.toDoubleOrZero(): Double {
    return try {
        this?.toDouble() ?: 0.0
    } catch (e: NumberFormatException) {
        0.0
    }
}