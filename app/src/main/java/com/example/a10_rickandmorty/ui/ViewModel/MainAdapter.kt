package com.example.a10_rickandmorty.ui.ViewModel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.a10_rickandmorty.data.Model.rnmData
import com.example.a10_rickandmorty.databinding.SingleRnmCharacterBinding

class MainAdapter(private val rnmDataList:ArrayList<rnmData>):

    RecyclerView.Adapter<MainAdapter.MainViewHolder>(){

    var observableData = MutableLiveData<rnmData>()

    inner class MainViewHolder(private val binding: SingleRnmCharacterBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(rnmDataItem: rnmData){

            binding.imgCharacter.setOnClickListener{
                observableData.value = rnmDataItem
            }

            binding.apply {
                tvName.text =rnmDataItem.name

                var recommendedSize = 300.0/(rnmDataItem.name.length.toFloat())
                recommendedSize= when{
                    (recommendedSize <22.0) -> 22.0
                    (recommendedSize >30.0) -> 30.0
                    else -> 26.0
                }

                tvName.textSize= recommendedSize.toFloat()
                tvSpecies.text =rnmDataItem.species

                Glide.with(imgCharacter.context).load(rnmDataItem.imageUrl).diskCacheStrategy(
                    DiskCacheStrategy.AUTOMATIC
                ).into(imgCharacter)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding =
            SingleRnmCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MainViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return rnmDataList.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

        holder.bind(rnmDataList[position])
    }

    fun addCharactersAndData(rnmDataListPassed: ArrayList<rnmData>)
    {
        this.rnmDataList.apply {
            clear()
            addAll(rnmDataListPassed)
        }
    }
}

