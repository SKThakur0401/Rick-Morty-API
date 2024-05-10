package com.example.a10_rickandmorty.ui.ViewModel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.a10_rickandmorty.R
import com.example.a10_rickandmorty.data.Model.rnmData
import com.example.a10_rickandmorty.databinding.ActivityPageDescriptionBinding

class PageDescription : AppCompatActivity() {

    lateinit var binding: ActivityPageDescriptionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityPageDescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        uploadDataOnPage()

        binding.btnBack.setOnClickListener{
            finish()
        }
    }


    private fun uploadDataOnPage()
    {
        var myData:rnmData = intent.getSerializableExtra("item_to_be_described") as rnmData

        binding.apply {
            tvDesName.text = "Name: " + myData.name
            tvDesSpecies.text = "Species: " +myData.species
            tvDesGender.text = "Gender: " +myData.gender
            tvDesStatus.text = "Status: " +myData.status
            tvDesLocation.text = "Location: " +myData.location.name
            tvDesOrigin.text = "Origin: " + myData.origin.name

            Glide.with(imgDesActivity.context).load(myData.imageUrl).diskCacheStrategy(
                DiskCacheStrategy.AUTOMATIC
            ).into(imgDesActivity)
        }
    }
}