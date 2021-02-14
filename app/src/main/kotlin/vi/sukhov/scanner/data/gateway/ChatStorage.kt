package vi.sukhov.scanner.data.gateway

import vi.sukhov.scanner.entity.ChatMessage

interface ChatStorage {

    fun sendMessage(msg: ChatMessage)

    fun updateMessage(msg: ChatMessage)

    fun removeMessage(msg: ChatMessage)

    fun replyMessage(replyId: String, msg: ChatMessage)

    fun getMessages(): List<ChatMessage>

}