package vi.sukhov.scanner.entity

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Order(
    var id: String? = null,
    var title: String? = null,     // Name order or other
    var code: String? = null,      // Order code
    var date: String? = null,
    var status: String? = null,
    var image: String? = null
)