package io.pdaa.chilenastats.di

import io.pdaa.chilenastats.BuildConfig
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    single(named("apiKey")) { BuildConfig.FOOTBAL_API_KEY }
    single(named("apiHost")) { BuildConfig.FOOTBAL_API_HOST }
    single(named("addsApplicationId")) { BuildConfig.ADDS_APPLICATION_ID }
    single(named("dashboardBannerId")) { BuildConfig.DASHBOARD_BANNER_ID }
}