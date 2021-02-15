package vi.sukhov.scanner.entity

import com.google.firebase.database.IgnoreExtraProperties
import vi.sukhov.scanner.util.Utils.getCurrentDate
import java.util.*

@IgnoreExtraProperties
data class ChatMessage(
    val id: String? = UUID.randomUUID().toString(),
    var message: String? = "",
    val author: String? = "",
    val date: String = getCurrentDate(),

    val orderId: String? = null
) {

    fun type(): Int = when {
        this.author == "system" -> MsgType.SYSTEM_MSG
        this.author == "system" && orderId != null -> MsgType.SYSTEM_MSG_ORDER
        this.author == "user" && orderId != null -> MsgType.USER_MSG_ORDER
        else -> MsgType.USER_MSG
    }

}