package com.olayg.halfwayapp.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.olayg.halfwayapp.databinding.ItemCharacterBinding
import com.olayg.halfwayapp.databinding.ItemGifBinding
import com.olayg.halfwayapp.util.layoutInflater
import com.olayg.halfwayapp.util.loadUrl


class GifAdapter(
    private val gifs: List<Pair<String, String>>,
) : RecyclerView.Adapter<GifAdapter.GifViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ) = GifViewHolder.getInstance(parent)

    override fun onBindViewHolder(holder: GifViewHolder, position: Int) {
        holder.loadGif(gifs[position])
    }

    override fun getItemCount() = gifs.size

    class GifViewHolder(
        private val binding: ItemGifBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun loadGif(pairs: Pair<String, String>) = with(binding) {
            ivContainer.loadUrl(pairs.first)
        }

        companion object {
            fun getInstance(parent: ViewGroup) = ItemGifBinding.inflate(
                parent.layoutInflater, parent, false
            ).run { GifViewHolder(this) }
        }
    }
}