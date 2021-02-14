package vi.sukhov.scanner.entity

data class ChatMessage(
    val id: String,
    val message: String,
    val author: String,
    val date: String,

    val order: Order? = null
)