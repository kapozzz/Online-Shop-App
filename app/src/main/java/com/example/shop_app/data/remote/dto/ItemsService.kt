package com.example.shop_app.data.remote.dto

import retrofit2.http.GET

interface ItemsService {

    @GET("/v3/97e721a7-0a66-4cae-b445-83cc0bcf9010")
    fun getItems(): ItemsResponse

}