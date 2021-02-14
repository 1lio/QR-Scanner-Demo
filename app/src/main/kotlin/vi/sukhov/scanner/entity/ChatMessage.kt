package vi.sukhov.scanner.entity

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class ChatMessage(
    val id: String,
    val message: String,
    val author: String,
    val date: String,

    val order: Order? = null
)