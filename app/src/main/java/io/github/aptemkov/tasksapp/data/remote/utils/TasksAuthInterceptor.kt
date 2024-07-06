package io.github.aptemkov.tasksapp.data.remote.utils

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * Данный класс необходим для добавления заголовка с авторизацией в сетевые запросы
 *
 */
class TasksAuthInterceptor @Inject constructor(
    private val bearerToken: String
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val request = originalRequest.newBuilder()
            .header("Authorization", "Bearer $bearerToken")
            .build()

        return chain.proceed(request)
    }
}