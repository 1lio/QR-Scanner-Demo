package vi.sukhov.scanner.ui.home.chat

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import vi.sukhov.scanner.R
import vi.sukhov.scanner.core.common.BaseFragment
import vi.sukhov.scanner.databinding.FragmentChatBinding

@AndroidEntryPoint
class ChatFragment : BaseFragment(R.layout.fragment_chat) {

    private val binding by viewBinding(FragmentChatBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}
