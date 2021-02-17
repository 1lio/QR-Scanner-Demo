package vi.sukhov.scanner.data.local.database.room.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import vi.sukhov.scanner.data.local.database.room.DB.TABLE_CHAT_MESSAGES

@Entity(tableName = TABLE_CHAT_MESSAGES)
class ChatMessageEntity(
    @PrimaryKey
    val id: String?,
    val authorId: String?,
    val message: String?,
    val date: String?,
)