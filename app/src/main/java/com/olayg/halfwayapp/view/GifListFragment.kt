package com.olayg.halfwayapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.olayg.halfwayapp.adapter.GifAdapter
import com.olayg.halfwayapp.databinding.FragmentGifListBinding

class GifListFragment : Fragment() {

    private var _binding: FragmentGifListBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<GifListFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentGifListBinding.inflate(
        inflater, container, false
    ).also { _binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadGifs()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun loadGifs() = with(binding) {
        rvGifs.adapter = GifAdapter(args.character.gifs)
        binding.progress.isVisible = false
    }

}