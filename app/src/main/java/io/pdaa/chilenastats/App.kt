package io.pdaa.chilenastats

import android.app.Application
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import io.pdaa.chilenastats.di.appModule
import io.pdaa.chilenastats.di.dataSourceModule
import io.pdaa.chilenastats.di.repositoryModule
import io.pdaa.chilenastats.di.useCaseModule
import io.pdaa.chilenastats.di.viewModelModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        //addMob setup
        CoroutineScope(Dispatchers.IO).launch {
            MobileAds.initialize(this@App)
        }
        // Configurar dispositivos de prueba
        if (BuildConfig.DEBUG) {
            val testDeviceIds = listOf("142387B6696ED53A771F198097D6ABCE")
            val configuration =
                RequestConfiguration.Builder().setTestDeviceIds(testDeviceIds).build()
            MobileAds.setRequestConfiguration(configuration)
        }

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(
                listOf(
                    appModule,
                    dataSourceModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                    )
            )
        }
    }
}

