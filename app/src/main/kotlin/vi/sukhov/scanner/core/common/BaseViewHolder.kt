package vi.sukhov.scanner.core.common

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun setOnClickListener(listener: View.OnClickListener) {
        itemView.setOnClickListener(listener)
    }

    abstract fun bind(item: T)

}
