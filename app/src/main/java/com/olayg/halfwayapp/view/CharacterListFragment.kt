package com.olayg.halfwayapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.olayg.halfwayapp.adapter.CharacterAdapter
import com.olayg.halfwayapp.databinding.FragmentCharacterListBinding
import com.olayg.halfwayapp.model.custom.Character
import com.olayg.halfwayapp.util.logMe
import com.olayg.halfwayapp.viewmodel.SSBViewModel

class CharacterListFragment : Fragment() {

    private var _binding: FragmentCharacterListBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<SSBViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentCharacterListBinding.inflate(
        inflater, container, false
    ).also { _binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initObservers() = with(viewModel) {
        characters.observe(viewLifecycleOwner) { characters ->
            binding.progress.isVisible = false
            binding.rvCharacters.adapter = CharacterAdapter(characters, ::characterSelected)
        }
    }

    private fun characterSelected(character: Character) = with(findNavController()) {
        "characterSelected $character".logMe()
        navigate(CharacterListFragmentDirections.goToGifList(character))
    }
}