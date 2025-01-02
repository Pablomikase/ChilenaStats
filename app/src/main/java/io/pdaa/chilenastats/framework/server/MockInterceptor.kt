package io.pdaa.chilenastats.framework.server

import android.util.Log
import io.pdaa.chilenastats.framework.server.mocks.CountriesMockResponse
import io.pdaa.chilenastats.framework.server.mocks.FixturesMockResponse
import io.pdaa.chilenastats.framework.server.mocks.LeaguesMockResponse
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

class MockInterceptor: Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        if (request.url.toString().contains("https://api-football-v1.p.rapidapi.com/v3/leagues")) {
            //Se crea Json fake para las respuestas
            Log.i("Interceptado", "Leagues intercepted")
            return Response.Builder()
                .code(200)
                .message("OK")
                .request(request)
                .protocol(Protocol.HTTP_1_1)
                .body(
                    LeaguesMockResponse.LEAGUES_RESPONSE.toResponseBody("application/json".toMediaTypeOrNull())
                )
                .addHeader("content-type", "application/json")
                .build()
        }

        if (request.url.toString().contains("https://api-football-v1.p.rapidapi.com/v3/countries")) {
            //Se crea Json fake para las respuestas de countries
            Log.i("Interceptado", "Countries intercepted")
            return Response.Builder()
                .code(200)
                .message("OK")
                .request(request)
                .protocol(Protocol.HTTP_1_1)
                .body(
                    CountriesMockResponse.COUNTRIES_RESPONSE.toResponseBody("application/json".toMediaTypeOrNull())
                )
                .addHeader("content-type", "application/json")
                .build()
        }

        if (request.url.toString().contains("https://v3.football.api-sports.io/fixtures")) {
            //Se crea Json fake para las respuestas de countries
            Log.i("Interceptado", "Fixtures intercepted")
            return Response.Builder()
                .code(200)
                .message("OK")
                .request(request)
                .protocol(Protocol.HTTP_1_1)
                .body(
                    FixturesMockResponse.FIXTURES_RESPONSE.toResponseBody("application/json".toMediaTypeOrNull())
                )
                .addHeader("content-type", "application/json")
                .build()
        }



        return chain.proceed(request)

    }
}