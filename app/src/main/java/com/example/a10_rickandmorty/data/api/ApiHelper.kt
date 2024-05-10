package com.example.a10_rickandmorty.data.api

class ApiHelper(private val apiService:ApiService) {

    suspend fun get_rnm_Data() = apiService.get_rnm_Data()
}

