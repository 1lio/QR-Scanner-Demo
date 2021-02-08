package vi.sukhov.scanner.entity

data class Order(
    val id: String?,
    val title: String?,           // Name order or other
    val date: String?,
    val status: String?,
    val image: String? = null
)