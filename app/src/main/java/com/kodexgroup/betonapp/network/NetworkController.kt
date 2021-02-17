package com.kodexgroup.betonapp.network

import okhttp3.*
import java.io.IOException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class NetworkController {

    private val client = OkHttpClient()

    suspend fun execute(request: Request) : String {
        return suspendCoroutine { continuation ->
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    continuation.resumeWithException(e)
                }

                override fun onResponse(call: Call, response: Response) {
                    response.use {
                        if (!response.isSuccessful)
                            continuation.resumeWithException(IOException("Unexpected code $response"))

                        continuation.resume(response.body!!.string())
                    }
                }
            })
        }
    }

}