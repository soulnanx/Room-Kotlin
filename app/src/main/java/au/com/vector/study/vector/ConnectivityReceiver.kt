package au.com.vector.study.vector

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager

class ConnectivityReceiver : BroadcastReceiver() {


    companion object {
        var onNetworkConnectionChange: ((Boolean) -> Any)? = null
    }

    override fun onReceive(context: Context?, p1: Intent?) {
        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = cm.activeNetworkInfo
        val isConnected = networkInfo != null && networkInfo.isConnectedOrConnecting
        onNetworkConnectionChange?.invoke(isConnected)
    }

    fun hasConnection() : Boolean{
        val cm = App.applicationContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = cm.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnectedOrConnecting
    }
}