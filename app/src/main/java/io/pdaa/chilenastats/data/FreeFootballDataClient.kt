package io.pdaa.chilenastats.data

import io.pdaa.chilenastats.BuildConfig
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.create

object FreeFootballDataClient {

    private val okHttpClient = okhttp3.OkHttpClient.Builder()
        .addInterceptor(::apiKeyAsQuery)
        .addInterceptor(MockInterceptor())
        .build()

    private val json = Json {
        ignoreUnknownKeys = true
    }

    val instance = Retrofit.Builder()
        .baseUrl("https://api-football-v1.p.rapidapi.com/v3/")
        .client(okHttpClient)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()
        .create<FreeFootballDataService>()

}

fun apiKeyAsQuery(chain: Interceptor.Chain) = chain.proceed(
    chain.request().newBuilder()
        .url(chain
            .request()
            .url
            .newBuilder()
            .addQueryParameter("x-rapidapi-key", BuildConfig.FOOTBAL_API_KEY)
            .build()
        )
        .url(chain
            .request()
            .url
            .newBuilder()
            .addQueryParameter("x-rapidapi-host", BuildConfig.FOOTBAL_API_HOST)
            .build()
        )
        .build()
)
