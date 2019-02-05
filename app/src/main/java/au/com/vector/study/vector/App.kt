package au.com.vector.study.vector

import android.app.Application
import android.content.Context
import android.content.IntentFilter
import android.net.ConnectivityManager
import com.facebook.stetho.Stetho

class App : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: App? = null

        fun applicationContext() : App {
            return instance!!.applicationContext as App
        }

    }

    public fun setConnectivityListener(listener : (Boolean) -> Any){
        ConnectivityReceiver.onNetworkConnectionChange = listener
    }

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        setupConnectivityReceiver()
    }

    private fun setupConnectivityReceiver() {
        val intentFilter = IntentFilter()
        intentFilter.addAction("android.net.wifi.STATE_CHANGED")
        intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED")
        registerReceiver(ConnectivityReceiver(), intentFilter)
    }
}