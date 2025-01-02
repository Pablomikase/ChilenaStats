package io.pdaa.chilenastats

import android.app.Application
import androidx.room.Room
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import io.pdaa.chilenastats.framework.database.FootballDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class App : Application() {
    lateinit var db: FootballDatabase
    override fun onCreate() {
        super.onCreate()
        //Data base setup
        db = Room.databaseBuilder(applicationContext, FootballDatabase::class.java, "football.db")
            .build()
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
    }
}