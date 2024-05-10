package com.example.a10_rickandmorty.data.api

import com.example.a10_rickandmorty.data.Model.rnmCompleteData
import retrofit2.http.GET

interface ApiService {

    @GET("api/character")
    suspend fun get_rnm_Data(): rnmCompleteData
}

