package com.example.a10_rickandmorty.ui.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.a10_rickandmorty.data.Model.rnmCompleteData
import com.example.a10_rickandmorty.data.Repository.HomeRepository
import com.example.a10_rickandmorty.utils.Resource
import com.example.a10_rickandmorty.utils.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainViewModel(var homeRepository: HomeRepository) : ViewModel(){

    var response = MutableLiveData<Resource<rnmCompleteData>>()

    suspend fun get_rnm_DataGetter()
    {
        response.value = Resource.loading(data=null)

        response.value= try{
            withContext(Dispatchers.IO)
            {
                Resource.success(data= homeRepository.get_rnm_Data())
            }

        } catch (e:Exception){
            Resource.error(data=null, msg= e.message?: "Unknown error occured")
        }
    }

}

