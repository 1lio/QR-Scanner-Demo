package vi.sukhov.scanner.util

import android.content.Context
import android.util.TypedValue
import android.util.TypedValue.COMPLEX_UNIT_DIP
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.google.android.material.snackbar.Snackbar
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object Utils {

    private val transitMap = mapOf(
        'а' to "a",
        'б' to "b",
        'в' to "v",
        'г' to "g",
        'д' to "d",
        'е' to "e",
        'ё' to "e",
        'ж' to "zh",
        'з' to "z",
        'и' to "i",
        'й' to "i",
        'к' to "k",
        'л' to "l",
        'м' to "m",
        'н' to "n",
        'о' to "o",
        'п' to "p",
        'р' to "r",
        'с' to "s",
        'т' to "t",
        'у' to "u",
        'ф' to "f",
        'х' to "h",
        'ц' to "c",
        'ч' to "ch",
        'ш' to "sh",
        'щ' to "sh",
        'ъ' to "",
        'ы' to "i",
        'ь' to "",
        'э' to "e",
        'ю' to "yu",
        'я' to "ya"
    )

    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts: List<String>? = fullName?.replaceAll("  ", " ")?.split(" ")
        val firstName = parts?.notEmptyOrNullAt(0)
        val lastName = parts?.notEmptyOrNullAt(1)

        return firstName to lastName
    }

    private fun String.replaceAll(oldValue: String, newValue: String): String {
        var result = this
        while (result.contains(oldValue)) {
            result = result.replace(oldValue, newValue)
        }
        return result
    }

    private fun List<String>.notEmptyOrNullAt(index: Int) =
        getOrNull(index).let { if ("" == it) null else it }

    fun transliteration(payload: String, divider: String = " ") = buildString {
        payload.forEach {
            append(
                when {
                    it == ' ' -> divider
                    it.isUpperCase() -> transitMap[it.toLowerCase()]?.capitalize(Locale.ROOT)
                        ?: it.toString()
                    else -> transitMap[it] ?: it.toString()
                }
            )
        }
    }

    fun toInitials(firstName: String?, lastName: String?): String? = when {
        firstName.isNullOrBlank() && lastName.isNullOrBlank() -> null
        !firstName.isNullOrBlank() && lastName.isNullOrBlank() -> firstName[0].toUpperCase()
            .toString()
        firstName.isNullOrBlank() && !lastName.isNullOrBlank() -> lastName[0].toUpperCase()
            .toString()
        !firstName.isNullOrBlank() && !lastName.isNullOrBlank() -> firstName[0].toUpperCase() + lastName[0].toUpperCase()
            .toString()
        else -> throw IllegalStateException("Incorrect state in 'when' expression")
    }

    fun convertPxToDp(context: Context, px: Int): Int {
        val scale = context.resources.displayMetrics.density
        return (px / scale + 0.5f).toInt()
    }

    fun convertDpToPx(context: Context, dp: Int): Int {
        val scale = context.resources.displayMetrics.density
        return (dp * scale + 0.5f).toInt()
    }

    fun convertSpToPx(context: Context, sp: Int): Int {
        return sp * context.resources.displayMetrics.scaledDensity.toInt()

    }


    fun Float.toDp(context: Context) =
        TypedValue.applyDimension(COMPLEX_UNIT_DIP, this, context.resources.displayMetrics)

    fun Int.toDp(context: Context): Int =
        TypedValue.applyDimension(
            COMPLEX_UNIT_DIP,
            this.toFloat(),
            context.resources.displayMetrics
        ).toInt()

    fun Float.toSp(context: Context) =
        TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            this,
            context.resources.displayMetrics
        )

    fun getCurrentDate(): String = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(Date())

}