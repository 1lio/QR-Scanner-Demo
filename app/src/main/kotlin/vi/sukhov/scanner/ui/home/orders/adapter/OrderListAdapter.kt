package vi.sukhov.scanner.ui.home.orders.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import vi.sukhov.scanner.core.common.BaseRecyclerAdapter
import vi.sukhov.scanner.core.common.BaseViewHolder
import vi.sukhov.scanner.databinding.ItemOrderBinding
import vi.sukhov.scanner.entity.Order
import vi.sukhov.scanner.ui.home.orders.ClickOrderListener

class OrderListAdapter(private val itemClickListener: ClickOrderListener) :
    BaseRecyclerAdapter<Order, OrderListAdapter.OrderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemPersonBinding = ItemOrderBinding.inflate(layoutInflater, parent, false)
        return OrderViewHolder(itemPersonBinding)
    }

    inner class OrderViewHolder(private val v: ItemOrderBinding) : BaseViewHolder<Order>(v.root) {

        override fun bind(item: Order) {
            v.title.text = item.title
            v.code.text = item.code
            v.status.text = item.status

            itemView.setOnClickListener {
                itemClickListener.onClick(item.id)
            }
        }
    }

}