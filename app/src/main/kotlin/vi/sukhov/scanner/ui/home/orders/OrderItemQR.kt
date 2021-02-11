package vi.sukhov.scanner.ui.home.orders

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import vi.sukhov.scanner.R
import vi.sukhov.scanner.ui.home.HomeActivity

@AndroidEntryPoint
class OrderItemQR @JvmOverloads constructor(context: Context, attr: AttributeSet? = null) :
    LinearLayout(context, attr) {

    private var viewModel: OrderItemViewModel =
        ViewModelProvider(context as HomeActivity)[OrderItemViewModel::class.java]

    init {

        val v = LayoutInflater.from(context).inflate(R.layout.item_order, this)

        val title: TextView = v.findViewById(R.id.title)
        val code: TextView = v.findViewById(R.id.code)
        val status: TextView = v.findViewById(R.id.status)

        visibility = View.GONE

        viewModel.orderData.observe(context as HomeActivity) {
            title.text = it.title
            code.text = it.code
            status.text = it.status
            visibility = if (title.text.isNullOrEmpty()) View.GONE else View.VISIBLE
        }

    }

    fun setOrderId(id: String) {
        viewModel.setOrderId(id)
    }

}
