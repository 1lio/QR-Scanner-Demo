package vi.sukhov.scanner.ui.home.orders.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import vi.sukhov.scanner.databinding.ItemOrderBinding
import vi.sukhov.scanner.entity.Order
import vi.sukhov.scanner.ui.home.orders.ClickOrderListener

class OrderListAdapter(private val itemClickListener: ClickOrderListener) :
    RecyclerView.Adapter<OrderListAdapter.OrderViewHolder>() {

    private val list: ArrayList<Order> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemPersonBinding = ItemOrderBinding.inflate(layoutInflater, parent, false)
        return OrderViewHolder(itemPersonBinding)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun updateListAndNotify(list: List<Order>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    inner class OrderViewHolder(private val v: ItemOrderBinding) : RecyclerView.ViewHolder(v.root) {

        fun bind(item: Order) {
            v.title.text = item.title
            v.code.text = item.code
            v.status.text = item.status

            itemView.setOnClickListener {
                itemClickListener.onClick(item.id)
            }
        }
    }

}