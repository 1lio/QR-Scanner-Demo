package vi.sukhov.scanner.ui.home.orders

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import vi.sukhov.scanner.R
import vi.sukhov.scanner.entity.Order
import vi.sukhov.scanner.ui.home.HomeActivity
import vi.sukhov.scanner.ui.home.orders.viewmodels.OrderItemViewModel
import vi.sukhov.scanner.core.common.Constants.IN_ORDER_ARG
import vi.sukhov.scanner.util.Utils.getCurrentDate

@AndroidEntryPoint
class OrderItemQR @JvmOverloads constructor(context: Context, attr: AttributeSet? = null) :
    LinearLayout(context, attr) {

    private var viewModel: OrderItemViewModel =
        ViewModelProvider(context as HomeActivity)[OrderItemViewModel::class.java]

    private val v = LayoutInflater.from(context).inflate(R.layout.item_order_qr, this)
    private val title: TextView = v.findViewById(R.id.title)
    private val code: TextView = v.findViewById(R.id.code)
    private val status: TextView = v.findViewById(R.id.status)
    private val buttonAdd: Button = v.findViewById(R.id.addToWarehouse)

    private val navController by lazy {
        Navigation.findNavController(context as HomeActivity, R.id.homeNavHostFragment)
    }


    init {

        visibility = View.GONE

        GlobalScope.launch (Dispatchers.Main) {
            viewModel.order.collect { order ->

                if (order != null) {

                    title.text = order.title
                    code.text = order.id
                    status.text = order.status

                    setVisibility(order)

                    visibility = if (title.text.isNullOrEmpty()) {
                        View.GONE
                    } else {
                        startSticking()
                        View.VISIBLE
                    }

                    buttonAdd.isEnabled = true
                    buttonAdd.setOnClickListener {
                        val newOrder = Order(
                            id = order.id,
                            title = context.getString(R.string.not_identified),
                            code = order.id,
                            date = getCurrentDate(),
                            status = context.getString(R.string.on_warehouse)
                        )

                        viewModel.addOrder(newOrder)

                        Toast.makeText(
                            context,
                            context.getString(R.string.saving),
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        it.isEnabled = false
                    }

                    setOnClickListener {
                        val bundle = bundleOf(IN_ORDER_ARG to order.id)
                        navController.navigate(
                            R.id.action_navigation_scanner_to_navigation_order_detail,
                            bundle
                        )
                    }
                }
            }


        }



    }


    private fun setVisibility(order: Order) {
        buttonAdd.visibility =
            if (order.status == context.getString(R.string.on_warehouse) ||
                order.status == context.getString(R.string.on_wait_list)
            ) View.GONE else View.VISIBLE
    }

    fun setOrderId(id: String) {
        viewModel.setOrderId(id)
    }

    // Прилипание карточки к экрану
    private var stickingJob: Job? = null
    private fun startSticking() {

        stickingJob?.cancel() ?: return

        stickingJob = GlobalScope.launch(Dispatchers.Main) {
            delay(HIDE_CARD_THROUGH_MILLISECONDS)
            visibility = View.GONE
        }

        stickingJob?.start() ?: return
    }

    private companion object {
        const val HIDE_CARD_THROUGH_MILLISECONDS = 8000L
    }
}

