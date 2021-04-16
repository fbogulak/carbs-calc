package link.epoczta.carbscalc.utils

import android.text.InputFilter
import android.text.Spanned
import java.text.DecimalFormatSymbols
import java.util.regex.Pattern

class DecimalDigitsInputFilter(digitsBeforeSeparator: Int, digitsAfterSeparator: Int) :
    InputFilter {

    companion object {
        private const val SEPARATORS = "[.,]"
    }

    private val pattern: Pattern
    private val separatorPattern: Pattern

    init {
        val b = "(\\d{1,$digitsBeforeSeparator})"
        val a = "(\\d{1,$digitsAfterSeparator})"
        val s = SEPARATORS

        val numberRegex = StringBuilder()
            .append("($s)")
            .append("|")
            .append("($b$s$a)")
            .append("|")
            .append("($b$s)")
            .append("|")
            .append("($b)")
            .append("|")
            .append("($s$a)")
            .toString()

        pattern = Pattern.compile(numberRegex)
        separatorPattern = Pattern.compile(s)
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

        val separatorMatcher = separatorPattern.matcher(input.firstOrNull()?.toString() ?: "")
        val isSeparatorFirst = separatorMatcher.matches()

        val localSeparator = DecimalFormatSymbols.getInstance().decimalSeparator.toString()
        return if (!matcher.matches())
            ""
        else if (isSeparatorFirst && source.isEmpty())
            "0"
        else if (isSeparatorFirst)
            "0$localSeparator"
        else
            source.replace(Regex("[.,]"), localSeparator)
    }
}
