package com.olayg.halfwayapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.olayg.halfwayapp.adapter.CharacterAdapter
import com.olayg.halfwayapp.databinding.FragmentSuperSmashListBinding
import com.olayg.halfwayapp.model.custom.Character
import com.olayg.halfwayapp.repo.local.DataStorePref
import com.olayg.halfwayapp.viewmodel.SSBViewModel
import kotlinx.coroutines.launch

class SuperSmashList : Fragment() {
    private var _binding: FragmentSuperSmashListBinding? = null
    private val binding get() = _binding!!
    private val ssbViewModel by activityViewModels<SSBViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = FragmentSuperSmashListBinding.inflate(
        inflater, container, false
    ).also { _binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeObservables()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeObservables() = with(ssbViewModel) {
        categories.observe(viewLifecycleOwner) { characters ->
            binding.rvCharacters.adapter = CharacterAdapter(characters, ::characterSelected)
        }
    }

    private fun characterSelected(character: Character) = with(findNavController()) {
        lifecycleScope.launch {
            context?.let { DataStorePref(it) }?.setCharStringData(character.name, character.guide)
            val action =
                SuperSmashListDirections.actionSuperSmashListFragmentToSuperSmashInfoFragment(
                    character
                )
            navigate(action)
        }

    }

}