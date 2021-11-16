package com.olayg.halfwayapp.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.olayg.halfwayapp.databinding.ItemGifBinding
import com.olayg.halfwayapp.model.response.Gif
import com.olayg.halfwayapp.util.layoutInflater
import com.olayg.halfwayapp.util.loadUrl

class GfyAdapter(
    private val gifs: List<String>,
) : RecyclerView.Adapter<GfyAdapter.GfyViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ) = GfyViewHolder.getInstance(parent)

    override fun onBindViewHolder(holder: GfyViewHolder, position: Int) {
        holder.loadGif(gifs[position])
    }

    override fun getItemCount() = gifs.size

    class GfyViewHolder(
        private val binding: ItemGifBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun loadGif(url: String) = with(binding) {
            ivGif.loadUrl(url)
        }

        companion object {
            fun getInstance(parent: ViewGroup) = ItemGifBinding.inflate(
                parent.layoutInflater, parent, false
            ).run { GfyViewHolder(this) }
        }
    }
}