package ru.delayvi.fruits.data.network

import retrofit2.http.GET
import ru.delayvi.fruits.domain.fruits.entity.Fruit

interface FruitsApi {

    @GET("all")
    suspend fun getAllFruits(): List<FruitNetworkEntity>

    @GET("apple")
    suspend fun getFruit(fruitName: String): FruitNetworkEntity
}