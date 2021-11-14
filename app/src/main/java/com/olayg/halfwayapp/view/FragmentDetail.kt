package com.olayg.halfwayapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.olayg.halfwayapp.adapter.GifAdapter
import com.olayg.halfwayapp.databinding.FragmentDetailBinding
import com.olayg.halfwayapp.model.custom.Character
import com.olayg.halfwayapp.viewmodel.SSBViewModel

class FragmentDetail : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private var _character: Character? = null
    private val character get() = _character!!

    private val viewModel by activityViewModels<SSBViewModel>()

    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View = FragmentDetailBinding.inflate(

        layoutInflater,
        container,
        false

    ).also {

        _binding = it

    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        _character = arguments?.getParcelable("character")

        initViews()

    }

    override fun onDestroyView() {

        super.onDestroyView()

        _binding = null
        _character = null

    }

    private fun initViews() {

        if (character.gifs.isNotEmpty()) {

            binding.rvCharacters.visibility = View.VISIBLE
            binding.tvMain.visibility = View.GONE

            binding.rvCharacters.adapter = GifAdapter(character.gifs)

        }

    }

}