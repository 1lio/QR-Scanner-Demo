package vi.sukhov.scanner.util.extensions

import android.widget.EditText

fun EditText.validateEmail(): Boolean {
    val email = this.text.toString().trim()
    return if (email.isEmpty()) {
        this.error = "Insert Something..."
        false
    } else {
        this.error = null
        true
    }
}

fun EditText.validatePassword(): Boolean {

    val password = this.text.toString().trim()

    return if (password.isEmpty()) {
        this.error = "Insert Something..."
        false
    } else {
        this.error = null
        true
    }
}