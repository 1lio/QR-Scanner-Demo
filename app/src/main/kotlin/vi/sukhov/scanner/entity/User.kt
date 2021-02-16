package vi.sukhov.scanner.entity

import com.google.firebase.database.IgnoreExtraProperties
import java.util.*

@IgnoreExtraProperties
data class User(
    val id: String? = UUID.randomUUID().toString(),
    val name: String? = ""
)