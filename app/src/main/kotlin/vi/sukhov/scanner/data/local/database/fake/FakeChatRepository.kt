package vi.sukhov.scanner.data.local.database.fake

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import vi.sukhov.scanner.data.gateway.ChatStorage
import vi.sukhov.scanner.entity.ChatMessage

object FakeChatRepository : ChatStorage {

    private val listChat = mutableListOf<ChatMessage>()

    override fun sendMessage(msg: ChatMessage) {
        listChat.add(msg)
    }

    override fun updateMessage(msg: ChatMessage) {
        listChat.forEach {
            if (msg == it) it.message = msg.message
        }
    }

    override fun removeMessage(msg: ChatMessage) {
        listChat.remove(msg)
    }

    override fun replyMessage(replyId: String, msg: ChatMessage) {
        // пока нет функционала комментирования
    }


    // Импровезированный чат
    override fun getMessages(): Flow<List<ChatMessage>> {

        return flow {

            listChat.add(
                ChatMessage(
                    message = "Привет! Я Вася!",
                    author = "UltraPower"
                )
            )
            emit(listChat)

            delay(3000)

            listChat.add(ChatMessage(message = "Привет Вася!", author = "system"))
            emit(listChat)

            delay(1000)
            listChat.add(
                ChatMessage(
                    message = "Как жизнь ?system",
                    author = "UltraPower"
                )
            )
            emit(listChat)

            listChat.add(ChatMessage(message = "...", author = "system"))
            delay(2000)
            listChat.add(ChatMessage(message = "Непонятна", author = "system"))
            emit(listChat)

            delay(1000)
            listChat.add(ChatMessage(message = "Понял принял", author = "UltraPower"))
            emit(listChat)

            delay(1000)
            listChat.add(ChatMessage(message = "...", author = "system"))
            emit(listChat)

            delay(2000)
            listChat.add(ChatMessage(message = "Непонятна", author = "system"))
            emit(listChat)

            delay(1000)
            listChat.add(ChatMessage(message = "...", author = "system"))
            emit(listChat)

            delay(2000)
            listChat.add(ChatMessage(message = "Не ухади...", author = "system")) // :)))
            emit(listChat)

            delay(1000)
            listChat.add(ChatMessage(message = "Я тут", author = "UltraPower"))
            emit(listChat)

            delay(1000)
            listChat.add(ChatMessage(message = "system leave chat", author = "system"))
            emit(listChat)

            // Далее просто обновляем
            while (true) {
                delay(100)
                emit(listChat)
            }
        }
    }
}
