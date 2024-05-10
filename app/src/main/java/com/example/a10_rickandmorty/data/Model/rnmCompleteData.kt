package com.example.a10_rickandmorty.data.Model

import com.google.gson.annotations.SerializedName

data class rnmCompleteData(

    @SerializedName("results")
    val rnmDataList : ArrayList<rnmData>
)
