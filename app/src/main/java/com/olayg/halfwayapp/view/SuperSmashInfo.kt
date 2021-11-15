package com.olayg.halfwayapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.olayg.halfwayapp.adapter.GfyAdapter
import com.olayg.halfwayapp.databinding.FragmentSuperSmashInfoBinding

class SuperSmashInfo : Fragment() {
    private var _binding: FragmentSuperSmashInfoBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<SuperSmashInfoArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = FragmentSuperSmashInfoBinding.inflate(
        inflater, container, false
    ).also { _binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initViews() = with(binding) {
        rvGifs.adapter = args.characterInfo.gifs?.let { GfyAdapter(it) }
        if (args.characterInfo.gifs.isNullOrEmpty()) {
            rvGifs.isVisible = false
            tvEmptyGifs.isVisible = true
        }
        tvName.text = args.characterInfo.name
        tvDescription.text = args.characterInfo.guide
        btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}