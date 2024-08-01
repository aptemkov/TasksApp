
package io.github.aptemkov.tasksapp.data.remote.audio

import android.util.Log
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.IOException

fun uploadFile(filePath: String, authorizationToken: String) {
    val client = OkHttpClient()

    val file = File(filePath)

    val requestBody = MultipartBody.Builder()
        .setType(MultipartBody.FORM)
        .addFormDataPart("file", file.name,
            file.asRequestBody("audio/mpeg".toMediaTypeOrNull())
        )
        .build()

    val request = Request.Builder()
        .url("https://file.io/")
        .addHeader("Authorization", "Bearer $authorizationToken")
        .addHeader("Accept", "application/json")
        .post(requestBody)
        .build()

    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            Log.i("LoadAudioFile", "onFailure: ${e.message}")
            e.printStackTrace()
        }

        override fun onResponse(call: Call, response: Response) {
            if (response.isSuccessful) {
                val responseBody = response.body?.string()
                Log.i("LoadAudioFile", "Response: $responseBody")

            } else {
                Log.i("LoadAudioFile", "Error: ${response.code}")
            }
        }
    })
}
