package io.pdaa.chilenastats.endToEnd

import android.app.Application
import io.pdaa.chilenastats.di.appModule
import io.pdaa.chilenastats.di.dataSourceModule
import io.pdaa.chilenastats.di.repositoryModule
import io.pdaa.chilenastats.di.useCaseModule
import io.pdaa.chilenastats.di.viewModelModule
import org.koin.core.context.GlobalContext.startKoin

class TestApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            listOf(
                appModule,
                dataSourceModule,
                repositoryModule,
                useCaseModule,
                viewModelModule
            )

        }
    }
}