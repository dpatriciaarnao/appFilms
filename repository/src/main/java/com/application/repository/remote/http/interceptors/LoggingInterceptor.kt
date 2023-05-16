package com.application.repository.remote.http.interceptors

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import okio.Buffer
import java.io.IOException

class LoggingInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val body = request.body
        val requestBody = if (body != null) {
            val buffer = Buffer()
            body.writeTo(buffer)
            buffer.readUtf8()
        } else {
            "null"
        }

        val requestLog = String.format(
            "Sending request %s %s on %s%n%s%s",
            request.method, request.url, chain.connection(),
            request.headers, "Body: $requestBody"
        )

        val response = chain.proceed(request)
        val bodyString = response.body?.string()

        Log.d("LoggingInterceptor", "Request\n$requestLog")
        Log.d("LoggingInterceptor", "Response(${request.method} ${response.code})\n$bodyString")

        return response.newBuilder()
            .body(bodyString!!.toResponseBody(response.body?.contentType()))
            .build()
    }
}
