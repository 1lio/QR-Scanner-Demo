package vi.sukhov.scanner.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.os.Build

object NetworkUtil {

    private fun checkNetworkInfo(
        context: Context,
        onConnectionStatusChange: OnConnectionStatusChange
    ) {

        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                ?: onConnectionStatusChange.onChange(false)

            connectivityManager.registerDefaultNetworkCallback(object : NetworkCallback() {

                override fun onAvailable(network: Network) {
                    onConnectionStatusChange.onChange(true)
                }

                override fun onLost(network: Network) {
                    onConnectionStatusChange.onChange(false)
                }
            })

        } else {
            val networkInfo = connectivityManager.activeNetworkInfo
            onConnectionStatusChange.onChange(networkInfo != null && networkInfo.isConnectedOrConnecting)
        }
    }

    // Вынес слушатель в лямбду + расширение. Вопрос про производительность.
    fun Context.onNetworkAvailableListener(callback: (isConnected: Boolean) -> Unit) {
        checkNetworkInfo(this, object : OnConnectionStatusChange {
            override fun onChange(type: Boolean) {
                callback(type)
            }
        })
    }

}


interface OnConnectionStatusChange {
    fun onChange(type: Boolean)
}