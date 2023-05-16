package com.application.repository.utils

import com.application.repository.remote.models.exception.NetworkException
import kotlinx.coroutines.delay
import java.net.ConnectException
import java.net.NoRouteToHostException
import java.net.PortUnreachableException
import java.net.ProtocolException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.net.UnknownServiceException

const val DEFAULT_RETRIES = 3
suspend fun <T> retryIO(
    times: Int = DEFAULT_RETRIES,
    delayMillis: Long = 0L,
    block: suspend () -> T
): T {
    repeat(times) {
        try {
            return block()
        } catch (e: Exception) {
            // if it's not a connectivity issue, does not worth retrying
            if (!e.isConnectivityIssue()) {
                return block()
            } else if (it == times - 1) { // the penultimate try
                throw NetworkException(e.message ?: "Connectivity issue")
            }
        }
        delay(delayMillis)
    }
    return block() // last attempt, but already changed to offline
}

private fun Exception.isConnectivityIssue(): Boolean {
    return this is SocketTimeoutException || this is ConnectException ||
        this is UnknownHostException || this is PortUnreachableException ||
        this is SocketException || this is UnknownServiceException ||
        this is ProtocolException || this is NoRouteToHostException ||
        this is NetworkException
}
