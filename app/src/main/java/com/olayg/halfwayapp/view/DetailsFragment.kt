package com.olayg.halfwayapp.view

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.map
import androidx.navigation.fragment.navArgs
import com.olayg.halfwayapp.adapter.CharacterAdapter
import com.olayg.halfwayapp.adapter.GifAdapter
import com.olayg.halfwayapp.databinding.FragmentDetailsBinding
import com.olayg.halfwayapp.model.custom.gfyItem
import com.olayg.halfwayapp.model.custom.giffy
import com.olayg.halfwayapp.model.response.Gif
import com.olayg.halfwayapp.util.loadUrl
import com.olayg.halfwayapp.viewmodel.GifViewModel

class DetailsFragment :Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    val args by navArgs<DetailsFragmentArgs>()
    //by viewModels<GifViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentDetailsBinding.inflate(inflater, container, false)
        .also { _binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       // viewModel.gifsData.observe(viewLifecycleOwner){}
        binding.rvGifs.adapter = GifAdapter()

        binding.closeBtn.setOnClickListener(){
           // this.postponeEnterTransition()

            activity?.supportFragmentManager?.popBackStack()
           // \getFragmentManager().popBackStack()
        }

        if(args.selectedChar.gifs != null){
           // loadGif(args.selectedChar.gifs)
            val listG = args.selectedChar.gifs
            loadGif(listG)
            (binding.rvGifs.adapter as GifAdapter).updateUrls(listG)
        }else
      //  binding.tvDesc.text = args.selectedChar.name +" does not have any Gifs :("

        Log.d("Click2","no gifs")
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun loadGif(gifs : List<Gif?>){


        val size = gifs.size
        gifArray = ArrayList(size)
        Log.d("Load","size "+size.toString())
        for(index in 0 until size)
        {
            val gif : Gif = args.selectedChar.gifs[index]!!
            val viewModel = GifViewModel(gif.url)
            val gItem = viewModel.gifsData.value
            val location : String = gItem?.content_urls?.pxGif?.url
                ?: "https://attra.ncat.org/images/error_graphic.jpg"
            gifArray.add(location)
            //gifArray.add(gItem.content_urls.pxGif.url)
            /*viewModel.gifsData.observe(viewLifecycleOwner){
                Log.d("Load","test")
                gifArray.add(it.content_urls.pxGif.url)
                Log.d("Load","garray "+ gifArray[index])
            }*/
            //gifArray.add((viewModel.gifsData as gfyItem).content_urls.pxGif.url)
           // Log.d("Gifs",gif.description )
        }
      //  binding.tvDesc.text = args.selectedChar.name
       // binding.ivGif.loadUrl(args.selectedChar.image?.icon)
    }

    companion object{
        lateinit var gifArray : ArrayList<String>
    }


}