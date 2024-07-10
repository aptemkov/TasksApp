package io.github.aptemkov.tasksapp.data.remote

import io.github.aptemkov.tasksapp.data.remote.models.AllElementsRequest
import io.github.aptemkov.tasksapp.data.remote.models.AllElementsResponse
import io.github.aptemkov.tasksapp.data.remote.models.ElementRequest
import io.github.aptemkov.tasksapp.data.remote.models.ElementResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface TasksApi {

    @GET("list")
    suspend fun getAllElements(): AllElementsResponse

    @PATCH("list")
    suspend fun updateElements(
        @Header("X-Last-Known-Revision") revision: Int,
        @Body elements: AllElementsRequest
    ): AllElementsResponse

    @GET("list/{id}")
    suspend fun getTaskById(
        @Path("id") id: String
    ): ElementResponse

    @POST("list")
    suspend fun addElement(
        @Header("X-Last-Known-Revision") revision: Int,
        @Body element: ElementRequest
    ): ElementResponse

    @PUT("list/{id}")
    suspend fun updateElement(
        @Header("X-Last-Known-Revision") revision: Int,
        @Path("id") id: String,
        @Body element: ElementRequest
    ): ElementResponse

    @DELETE("list/{id}")
    suspend fun deleteElement(
        @Header("X-Last-Known-Revision") revision: Int,
        @Path("id") id: String
    ): ElementResponse
}