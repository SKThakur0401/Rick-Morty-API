package com.example.a10_rickandmorty.ui.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a10_rickandmorty.data.Model.rnmCompleteData
import com.example.a10_rickandmorty.data.Model.rnmData
import com.example.a10_rickandmorty.data.api.ApiHelper
import com.example.a10_rickandmorty.data.api.RetrofitBuilder
import com.example.a10_rickandmorty.databinding.ActivityMainBinding
import com.example.a10_rickandmorty.ui.ViewModel.MainAdapter
import com.example.a10_rickandmorty.ui.ViewModel.MainViewModel
import com.example.a10_rickandmorty.ui.ViewModel.PageDescription
import com.example.a10_rickandmorty.ui.factory.ViewModelFactory
import com.example.a10_rickandmorty.utils.Status
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding
    lateinit var myMainViewModel: MainViewModel
    lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        connectAdapter()
        setupViewModel()
        setupObserver()
    }

    fun setupObserver() {
        myMainViewModel.response.observe(this) {

                resource ->
            when (resource.status) {

                (Status.success) -> {           // Status will be success iff data from api is obtained
                    binding.rvRnm.visibility = View.VISIBLE
                    binding.btnRefresh.visibility = View.VISIBLE
                    resource.data?.let { resource_data -> putDataInAdapter(resource_data) }
                }

                (Status.loading)->{
                    binding.rvRnm.visibility = View.GONE
                    binding.btnRefresh.visibility = View.GONE
                }

                (Status.error)-> {
                    binding.rvRnm.visibility= View.VISIBLE
                    binding.btnRefresh.visibility= View.VISIBLE
                }

                else -> {}
            }
        }

        lifecycleScope.launch() {
            myMainViewModel.get_rnm_DataGetter()
        }
    }

    fun putDataInAdapter(myData: rnmCompleteData)   // After obtaining data from observer
    {                                               // We will insert that data in adapter
        adapter?.apply {                // using this function

            addCharactersAndData(myData.rnmDataList)
            notifyDataSetChanged()
        }
    }

    fun setupViewModel()        // This function is simply to create an object of the "ViewModel"
    {        // This object will later be used to fetch data from API using get_rnm_DataGetter() fun
        myMainViewModel= ViewModelProvider(this, ViewModelFactory(ApiHelper(RetrofitBuilder.apiService)))
            .get(MainViewModel::class.java)
    }

    fun connectAdapter()
    {
        adapter= MainAdapter(arrayListOf<rnmData>())        // here we are initialising the adapter empty,
                                                // We will put data in adapter later using fun
                                        // "putDataInAdapter"
        binding.rvRnm.adapter= adapter
        binding.rvRnm.layoutManager= LinearLayoutManager(this)


        adapter.observableData.observe(this){

            var intent= Intent(this, PageDescription::class.java)
            intent.putExtra("item_to_be_described" , it)
            startActivity(intent)
        }
    }
}

