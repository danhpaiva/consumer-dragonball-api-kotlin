package com.example.consumerdragonballapi

import com.example.consumerdragonballapi.model.Personagem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DbzApi {
    @GET("characters/{id}")
    suspend fun getPersonagem(@Path("id") id: String) : Response<Personagem>
}