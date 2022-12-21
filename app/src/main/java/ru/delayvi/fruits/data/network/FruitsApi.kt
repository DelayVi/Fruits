package ru.delayvi.fruits.data.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.delayvi.fruits.data.network.entity.FruitNetworkEntity

interface FruitsApi {

    @GET("fruit/all")
    suspend fun getAllFruits(): List<FruitNetworkEntity>

    @GET("fruit/{fruitName}")
    suspend fun getFruit(
        @Path("fruitName") fruitName: String
    ): FruitNetworkEntity
}