package com.olayg.halfwayapp.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.olayg.halfwayapp.adapter.CharacterAdapter
import com.olayg.halfwayapp.adapter.GifAdapter
import com.olayg.halfwayapp.databinding.FragmentDetailBinding
import com.olayg.halfwayapp.model.custom.Character
import com.olayg.halfwayapp.model.response.Gif
import com.olayg.halfwayapp.viewmodel.SSBViewModel

class DetailFragment : Fragment() {
    private val TAG = "DetailFragment"
    private val args by navArgs<DetailFragmentArgs>()
    lateinit var binding: FragmentDetailBinding
    private val viewModel: SSBViewModel by activityViewModels()
//    private val newsAdapter by lazy { CharacterAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated: ${args}")
        binding.apply {
            btn.setOnClickListener {
                findNavController().popBackStack()
            }
            rvGifs.adapter = GifAdapter(args.charResponse.gifs, ::gifSelected)
            tvName.text = args.charResponse.name
        }
        Log.d(TAG, "onViewCreated: ${args.charResponse.gifs.size}")
    }
    private fun gifSelected(gif: Gif) {
        Log.d(TAG, "gifSelected: $gif")
    }

}