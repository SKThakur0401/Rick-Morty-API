package com.example.a10_rickandmorty.data.Model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class NameUrl(
    @SerializedName("name")
    var name:String,

    @SerializedName("url")
    var url:String
):Serializable
