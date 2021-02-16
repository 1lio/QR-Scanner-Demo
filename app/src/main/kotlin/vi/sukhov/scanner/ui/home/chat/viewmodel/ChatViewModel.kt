package vi.sukhov.scanner.ui.home.chat.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import vi.sukhov.scanner.data.gateway.ChatStorage
import vi.sukhov.scanner.data.gateway.PreferenceStorage
import vi.sukhov.scanner.entity.ChatMessage
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val localStorage: PreferenceStorage,
    private val repository: ChatStorage
) : ViewModel() {

    @Volatile
    var userName = ""

    init {
        viewModelScope.launch {
            localStorage.getUser().collect {
                userName = it.name ?: ""
            }
        }
    }

    fun sendMessage(msg: String) {

        val message = ChatMessage(
            author = userName,
            message = msg
        )

        if(msg.isNotEmpty())
        repository.sendMessage(message)
    }

    fun flowChat() = repository.getMessages()

}