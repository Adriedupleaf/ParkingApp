package com.example.data.api

import com.example.data.models.ParkingSpot
import retrofit2.http.GET

interface ParkApi {
    @GET("park/parkingSpots")
    suspend fun getParkingSpots(): List<ParkingSpot>


//    @Headers("Content-Type: application/json")
//    @POST("api/Category/categories")
//    suspend fun setCategories(@Body request: RequestSet): Response<Unit>
//    @Headers("Content-Type: application/json")
//    @DELETE("api/Category/categories/{id}")
//    suspend fun deleteCategories(@Path("id") id: Int): Response<Unit>
//    @Headers("Content-Type: application/json")
//    @PUT("api/Category/{id}")
//    suspend fun changeCategoriesName(@Path("id") id: Int,@Body request: RequestSet): Response<Unit>

}
