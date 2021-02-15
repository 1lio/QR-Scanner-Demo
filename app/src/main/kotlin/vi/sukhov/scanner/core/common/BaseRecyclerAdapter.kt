package vi.sukhov.scanner.core.common

import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<T, VH : BaseViewHolder<T>> : RecyclerView.Adapter<VH>() {

    var list: List<T> = ArrayList()

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int = list.size

    private fun getItem(i: Int): T = list[i]

    fun addAllAndNotify(items: List<T>) {
        this.list = items
        notifyDataSetChanged()
    }
}