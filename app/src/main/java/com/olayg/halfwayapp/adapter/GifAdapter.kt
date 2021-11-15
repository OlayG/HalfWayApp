package com.olayg.halfwayapp.adapter

import android.net.Uri
import android.util.Log
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.olayg.halfwayapp.databinding.ItemGifsBinding
import com.olayg.halfwayapp.model.response.Gif
import com.olayg.halfwayapp.util.layoutInflater
import com.olayg.halfwayapp.util.loadUrl
import com.olayg.halfwayapp.view.MainFragmentDirections

class GifAdapter(
    private val gifs: List<Gif>,
    private val selectedGif: (Gif) -> Unit
) : RecyclerView.Adapter<GifAdapter.GifViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ) = GifAdapter.GifViewHolder.getInstance(parent).apply {
        itemView.setOnClickListener { selectedGif(gifs[adapterPosition]) }
    }
    private val TAG = ""
    override fun onBindViewHolder(holder: GifViewHolder, position: Int) {
        holder.loadGif(gifs[position])
            gifs[position]
    }

    override fun getItemCount() = gifs.size

    class GifViewHolder(
        private val binding: ItemGifsBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        private val TAG = "GifAdapter"
        fun loadGif(gif: Gif) = with(binding) {
            val gifUrl = if (gif.url == "unluckyhardgnu") "UnluckyHardGnu" else gif.url
            val uri = Uri.parse("https://thumbs.gfycat.com/$gifUrl-mobile.mp4")
            tvDescription.text = gif.description
            vidvGif.start()
            vidvGif.setVideoURI(uri)
            Log.d(TAG, "loadGif: $uri")
        }


        companion object {
            fun getInstance(parent: ViewGroup) = ItemGifsBinding.inflate(
                parent.layoutInflater, parent, false
            ).run { GifViewHolder(this) }
        }
    }
}