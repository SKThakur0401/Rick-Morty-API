package com.example.a10_rickandmorty.data.Repository

import com.example.a10_rickandmorty.data.Model.rnmCompleteData
import com.example.a10_rickandmorty.data.api.ApiHelper

class HomeRepository(val apiHelper:ApiHelper) {

    suspend fun get_rnm_Data(): rnmCompleteData
    {
        return apiHelper.get_rnm_Data()
    }
}

