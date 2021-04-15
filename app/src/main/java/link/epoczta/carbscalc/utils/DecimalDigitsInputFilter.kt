package link.epoczta.carbscalc.utils

import android.text.InputFilter
import android.text.Spanned
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*
import java.util.regex.Pattern

class DecimalDigitsInputFilter(digitsBeforeSeparator: Int, digitsAfterSeparator: Int) :
    InputFilter {

    companion object {
        private const val SEPARATORS = "[\\.\\,]"
    }

    private val pattern: Pattern

    init {
        val b = "(-?\\d{1,$digitsBeforeSeparator})"
        val a = "(\\d{1,$digitsAfterSeparator})"
        val s = SEPARATORS

        val numberRegex = StringBuilder()
            .append("(-)")
            .append("|")
            .append("($b$s$a)")
            .append("|")
            .append("($b$s)")
            .append("|")
            .append("($b)")
            .toString()

        pattern = Pattern.compile(numberRegex)
    }


    override fun filter(
        source: CharSequence,
        start: Int,
        end: Int,
        dest: Spanned,
        dstart: Int,
        dend: Int
    ): CharSequence {

        val input =
            dest.toString().substring(0, dstart) + source.subSequence(start, end) + dest.toString()
                .substring(dend)

        val matcher = pattern.matcher(input)

        return if (!matcher.matches()) {
            ""
        } else {
            val localSeparator = DecimalFormatSymbols.getInstance().decimalSeparator.toString()
            source.replace(Regex("[.,]"), localSeparator)
        }
    }
}