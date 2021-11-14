package com.olayg.halfwayapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.olayg.halfwayapp.adapter.CharacterAdapter
import com.olayg.halfwayapp.databinding.FragmentMainBinding
import com.olayg.halfwayapp.model.custom.Character
import com.olayg.halfwayapp.viewmodel.SSBViewModel

class FragmentMain : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel by activityViewModels<SSBViewModel>()

    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    
    ): View = FragmentMainBinding.inflate(

        layoutInflater,
        container,
        false

    ).also { _binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        viewModel.characters.observe(viewLifecycleOwner) { characters ->

            binding.rvCharacters.adapter = CharacterAdapter(characters, ::characterSelected)

        }

    }

    private fun characterSelected(character: Character) {}

}