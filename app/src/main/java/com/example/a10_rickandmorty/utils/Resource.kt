package com.example.a10_rickandmorty.utils

data class Resource<T>(
    var status:Status,
    var data :T?,
    var msg:String?
){

    companion object{

        fun<T> success(data:T) = Resource(data=data, status= Status.success, msg=null)

        fun<T> error(data:T?, msg:String?) = Resource(data=data, status= Status.error, msg=msg)

        fun<T> loading(data:T?) = Resource(data=data, status= Status.loading, msg=null)
    }
}

