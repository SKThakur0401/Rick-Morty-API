package com.example.a10_rickandmorty.ui.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.a10_rickandmorty.data.Repository.HomeRepository
import com.example.a10_rickandmorty.data.api.ApiHelper
import com.example.a10_rickandmorty.ui.ViewModel.MainViewModel
import kotlinx.coroutines.handleCoroutineException

class ViewModelFactory(val apiHelper: ApiHelper): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if(modelClass.isAssignableFrom(MainViewModel::class.java))
        {
            return MainViewModel(HomeRepository(apiHelper)) as T
        }

        throw java.lang.IllegalArgumentException("Unknown class name")
    }
}