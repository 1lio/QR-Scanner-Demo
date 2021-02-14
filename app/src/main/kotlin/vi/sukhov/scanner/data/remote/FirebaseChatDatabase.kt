package vi.sukhov.scanner.data.remote

import vi.sukhov.scanner.data.gateway.ChatStorage
import vi.sukhov.scanner.entity.ChatMessage

object FirebaseChatDatabase : ChatStorage {

    override fun sendMessage(msg: ChatMessage) {
        //
    }

    override fun updateMessage(msg: ChatMessage) {
        //
    }

    override fun removeMessage(msg: ChatMessage) {
        //
    }

    override fun replyMessage(replyId: String, msg: ChatMessage) {
        //
    }

    override fun getMessages(): List<ChatMessage> {
        return emptyList()
    }
}