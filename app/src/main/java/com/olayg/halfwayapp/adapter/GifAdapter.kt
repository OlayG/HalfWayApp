package com.olayg.halfwayapp.adapter

import android.net.Uri
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.olayg.halfwayapp.databinding.ItemGifBinding
import com.olayg.halfwayapp.model.response.Gif
import com.olayg.halfwayapp.util.layoutInflater

class GifAdapter(

    private val gifs: List<Gif>

) : RecyclerView.Adapter<GifAdapter.GifViewHolder>() {

    override fun onCreateViewHolder(

        parent: ViewGroup,
        viewType: Int

    ) = GifViewHolder.getInstance(parent)

    override fun onBindViewHolder(holder: GifViewHolder, position: Int) {

        holder.loadGif(gifs[position])

    }

    override fun getItemCount() = gifs.size

    class GifViewHolder(

        private val binding: ItemGifBinding

    ) : RecyclerView.ViewHolder(binding.root) {

        companion object {

            fun getInstance(parent: ViewGroup) = ItemGifBinding.inflate(

                parent.layoutInflater,
                parent,
                false

            ).run { GifViewHolder(this) }

        }

        fun loadGif(gif: Gif) = with(binding) {

            val source = if (gif.url == "unluckyhardgnu") "UnluckyHardGnu" else gif.url

            val uri = Uri.parse("https://thumbs.gfycat.com/$source-mobile.mp4")

            ivPhoto.setOnCompletionListener { it.start() }

            ivPhoto.setVideoURI(uri)
            ivPhoto.start()

            tvName.text = gif.description

        }

    }

}