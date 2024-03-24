package com.example.shop_app.data.remote

import com.example.shop_app.data.remote.dto.ItemsResponse
import retrofit2.http.GET

interface ItemsService {

    @GET("b8095430-38e3-41c5-81f7-9d9e541ed97c")
    suspend fun getItems(): ItemsResponse

}

/* https://run.mocky.io/v3/d2c4c225-dad4-482f-b9c4-0cf171e0ea64 */