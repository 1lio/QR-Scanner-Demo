package vi.sukhov.scanner.ui.home.chat.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import vi.sukhov.scanner.R
import vi.sukhov.scanner.core.common.BaseRecyclerAdapter
import vi.sukhov.scanner.core.common.BaseViewHolder
import vi.sukhov.scanner.databinding.ChatItemBinding
import vi.sukhov.scanner.databinding.ChatItemOrderBinding
import vi.sukhov.scanner.entity.ChatMessage
import vi.sukhov.scanner.entity.MsgType

class ChatRecyclerAdapter : BaseRecyclerAdapter<ChatMessage, BaseViewHolder<ChatMessage>>() {

    private var ctx: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ChatMessage> {
        ctx = parent.context
        val inflater = LayoutInflater.from(ctx)

        val msgBinding = ChatItemBinding.inflate(inflater, parent, false)
       // val msgOrderBinding = ChatItemOrderBinding.inflate(inflater, parent, false)

        return when (viewType) {
            MsgType.SYSTEM_MSG -> MsgSystemViewHolder(msgBinding)
           // MsgType.SYSTEM_MSG_ORDER -> MsgSystemAndOrderViewHolder(msgOrderBinding)
           // MsgType.USER_MSG_ORDER -> MsgUserAndOrderViewHolder(msgOrderBinding)
            else -> MsgUserViewHolder(msgBinding)
        }
    }

    override fun getItemViewType(position: Int): Int = list[position].type()

    // ViewHolders

    inner class MsgUserViewHolder(private val v: ChatItemBinding) :
        BaseViewHolder<ChatMessage>(v.root) {
        override fun bind(item: ChatMessage) {
            v.username.text = item.author
            v.username.setTextColor(ctx!!.getColor(R.color.black))
            v.message.text = item.message
        }

    }

    inner class MsgUserAndOrderViewHolder(private val v: ChatItemOrderBinding) :
        BaseViewHolder<ChatMessage>(v.root) {
        override fun bind(item: ChatMessage) {
            v.chatUsername.text = item.author
            v.chatUsername.setTextColor(ctx!!.getColor(R.color.black))
            v.chatMessage.text = item.message
            if (item.orderId != null) {
                v.chatOrder.setOrderId(item.orderId)
            }
        }

    }

    inner class MsgSystemViewHolder(private val v: ChatItemBinding) :
        BaseViewHolder<ChatMessage>(v.root) {
        override fun bind(item: ChatMessage) {
            v.username.text = item.author

            v.username.setTextColor(ctx!!.getColor(R.color.red))
            v.message.text = item.message
        }

    }

    inner class MsgSystemAndOrderViewHolder(private val v: ChatItemOrderBinding) :
        BaseViewHolder<ChatMessage>(v.root) {
        override fun bind(item: ChatMessage) {
            v.chatUsername.text = item.author
            v.chatUsername.setTextColor(ctx!!.getColor(R.color.red))
            v.chatMessage.text = item.message
            if (item.orderId != null) {
                v.chatOrder.setOrderId(item.orderId)
            }
        }

    }

}
