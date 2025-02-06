package io.pdaa.chilenastats.framework.server

import io.pdaa.chilenastats.BuildConfig
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.create

object FreeFootballDataClient {

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient = okhttp3.OkHttpClient.Builder()
        .addInterceptor(::buildHeaders)
        /*.addInterceptor(MockInterceptor())*/
        /*.addInterceptor(loggingInterceptor)*/
        .build()

    private val json = Json {
        ignoreUnknownKeys = true
    }

    val instance = Retrofit.Builder()
        .baseUrl("https://v3.football.api-sports.io/")
        .client(okHttpClient)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()
        .create<FootballDataService>()

}
fun buildHeaders(chain: Interceptor.Chain): okhttp3.Response {
    val originalRequest = chain.request()
    val newRequest = originalRequest.newBuilder()
        .addHeader("x-rapidapi-key", BuildConfig.FOOTBAL_API_KEY)
        .addHeader("x-apisports-key", BuildConfig.FOOTBAL_API_KEY)
        .addHeader("x-rapidapi-host", BuildConfig.FOOTBAL_API_HOST)
        .build()
    return chain.proceed(newRequest)
}
