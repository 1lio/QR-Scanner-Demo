package vi.sukhov.scanner.entity

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(
    val id: String,
    val name: String?
)