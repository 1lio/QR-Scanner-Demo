package vi.sukhov.scanner.entity

data class Order(
    var id: String?,
    var title: String?,     // Name order or other
    var code: String?,      // Order code
    var date: String?,
    var status: String?,
    var image: String? = null
)