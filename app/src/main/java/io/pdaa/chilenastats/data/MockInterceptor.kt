package io.pdaa.chilenastats.data

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

            return Response.Builder()
                .code(200)
                .message("OK")
                .request(request)
                .protocol(Protocol.HTTP_1_1)
                .body(
                    MockData.LEAGUES_RESPONSE.toResponseBody("application/json".toMediaTypeOrNull())
                )
                .addHeader("content-type", "application/json")
                .build()
        }
        return chain.proceed(request)

    }
}