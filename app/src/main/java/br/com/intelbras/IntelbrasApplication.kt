package br.com.intelbras

import android.app.Application
import br.com.intelbras.IntelbrasApplication
import com.facebook.stetho.Stetho
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.raizlabs.android.dbflow.config.FlowConfig
import com.raizlabs.android.dbflow.config.FlowManager
import retrofit2.Retrofit
import kotlin.jvm.Synchronized

class IntelbrasApplication : Application() {
    val TAG = javaClass.simpleName
    override fun onCreate() {
        super.onCreate()
        instance = this
        Stetho.initializeWithDefaults(this)
        FlowManager.init(FlowConfig.Builder(this).build())
    }

    val isNetworkAvailable: Boolean
        get() {
            val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
            var activeNetworkInfo: NetworkInfo? = null
            if (connectivityManager != null) {
                activeNetworkInfo = connectivityManager.activeNetworkInfo
            }
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }

    companion object {
        @get:Synchronized
        var instance: IntelbrasApplication? = null
    }
}