package vi.sukhov.scanner.ui.home.chat

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import vi.sukhov.scanner.R
import vi.sukhov.scanner.core.common.BaseFragment
import vi.sukhov.scanner.databinding.FragmentChatBinding
import vi.sukhov.scanner.ui.home.chat.adapter.ChatRecyclerAdapter
import vi.sukhov.scanner.ui.home.chat.viewmodel.ChatViewModel
import vi.sukhov.scanner.ui.home.orders.adapter.OrderListAdapter
import vi.sukhov.scanner.ui.home.orders.viewmodels.OrdersViewModel

@AndroidEntryPoint
class ChatFragment : BaseFragment(R.layout.fragment_chat) {

    private val binding: FragmentChatBinding by viewBinding()
    private val viewModel: ChatViewModel by viewModels()

    private val listAdapter by lazy { ChatRecyclerAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()
        initEdit()
        observeList()

    }

    private fun initEdit() {

        binding.sendButton.setOnClickListener {
            val msgInput = binding.msgInput.editText

            viewModel.sendMessage(
                msgInput?.text.toString().trim()
            )

            msgInput?.setText("")
            binding.recyclerChat.adapter!!.notifyDataSetChanged()

        }

    }

    private fun initRecycler() {
        binding.recyclerChat.run {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter

        }
    }

    private fun observeList() {
        lifecycleScope.launchWhenStarted {
            viewModel.flowChat().collect { msg ->
                listAdapter.addAllAndNotify(msg)
                binding.recyclerChat.smoothScrollToPosition(msg.size)
            }
        }
    }

}
