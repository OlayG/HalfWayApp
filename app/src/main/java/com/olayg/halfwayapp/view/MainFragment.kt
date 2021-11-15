package com.olayg.halfwayapp.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.olayg.halfwayapp.adapter.CharacterAdapter
import com.olayg.halfwayapp.databinding.FragmentMainBinding
import com.olayg.halfwayapp.model.custom.Character
import com.olayg.halfwayapp.model.response.CharacterResponse
import com.olayg.halfwayapp.viewmodel.SSBViewModel

class MainFragment : Fragment() {
    private val TAG = "ListFragment"

    lateinit var binding: FragmentMainBinding
    private val viewModel: SSBViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.categories.observe(requireActivity()) { characters ->
            binding.rvCharacters.adapter = CharacterAdapter(characters, ::characterSelected)
            Log.d(TAG, "onViewCreated: ${characters.size}")
        }
    }

    private fun characterSelected(character: Character) {
        Log.d(TAG, "characterSelected: $character")
    }
}