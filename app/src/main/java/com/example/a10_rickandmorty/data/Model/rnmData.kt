package com.example.a10_rickandmorty.data.Model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class rnmData(

    @SerializedName("name")
    var name: String,

    @SerializedName("species")
    var species: String,

    @SerializedName("image")
    var imageUrl:String,

    @SerializedName("gender")
    var gender:String,

    @SerializedName("status")
    var status:String,

    @SerializedName("origin")
    var origin: NameUrl,

    @SerializedName("location")
    var location: NameUrl
):Serializable
