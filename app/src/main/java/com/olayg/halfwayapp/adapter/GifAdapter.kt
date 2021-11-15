package com.olayg.halfwayapp.adapter

import android.telecom.Call
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.olayg.halfwayapp.databinding.GifLayoutBinding
import com.olayg.halfwayapp.databinding.ItemCharacterBinding
import com.olayg.halfwayapp.model.custom.Character
import com.olayg.halfwayapp.model.response.Gif
import com.olayg.halfwayapp.util.layoutInflater
import com.olayg.halfwayapp.util.loadUrl
import com.olayg.halfwayapp.viewmodel.GifViewModel
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.olayg.halfwayapp.view.DetailsFragment

class GifAdapter: RecyclerView.Adapter<GifAdapter.GifViewHolder>() {


    private val urls = mutableListOf<Gif?>()




    class GifViewHolder(private val binding : GifLayoutBinding)
        : RecyclerView.ViewHolder(binding.root){

        //val vm = this.

        fun loadImage(url: String)=with(binding){
            ivGif.loadUrl(url)
           // tvDesc.text = url?.description

        }

        fun loadDesc(url: Gif?)=with(binding){
            // ivGif.loadUrl(url?.url)
            tvDesc.text = url?.description

        }


        companion object{
            fun getInstance(parent: ViewGroup) : GifViewHolder{
                val binding =   GifLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,false
                )
                return GifViewHolder(binding)
            }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifViewHolder {
        return GifViewHolder.getInstance(parent)
    }

    override fun onBindViewHolder(holder: GifViewHolder, position: Int) {
        if(DetailsFragment.gifArray[position] != null)
        holder.loadImage(DetailsFragment.gifArray[position])
        holder.loadDesc(urls[position])
        //DetailsFragment.
    }

    override fun getItemCount(): Int {
        return urls.size
    }

    fun updateUrls(urls: List<Gif?>){
        val size = this.urls.size
        this.urls.clear()
        notifyItemRangeRemoved(0,size)

        this.urls.addAll(urls)
        notifyItemRangeInserted(0, urls.size)
    }
}
