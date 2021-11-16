package com.olayg.halfwayapp.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.olayg.halfwayapp.R
import com.olayg.halfwayapp.adapter.CharacterAdapter
import com.olayg.halfwayapp.databinding.FragmentMainBinding
import com.olayg.halfwayapp.model.custom.Character
import com.olayg.halfwayapp.viewmodel.SSBViewModel

class FragmentMain : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel by activityViewModels<SSBViewModel>()

    private val sharedPreferences by lazy { activity?.getSharedPreferences("PREFERENCES", Context.MODE_PRIVATE) }

    private lateinit var characters: List<Character>

    private lateinit var names: MutableList<String>
    private lateinit var portraits: MutableList<String>
    private lateinit var gifs: MutableList<String>

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

        names = (sharedPreferences?.getString("NAMES", null)?.split(", ") ?: mutableListOf()) as MutableList<String>
        portraits = (sharedPreferences?.getString("PORTRAITS", null)?.split(", ") ?: mutableListOf()) as MutableList<String>
        gifs = (sharedPreferences?.getString("GIFS", null)?.split(", ") ?: mutableListOf()) as MutableList<String>

        if (names.isNotEmpty() && portraits.isNotEmpty())
            binding.rvCharacters.adapter = CharacterAdapter(names, portraits, ::characterSelected)

        else viewModel.characters.observe(viewLifecycleOwner) {

            characters = it

            it.forEach { character ->

                names.add(character.name)
                portraits.add(character.image?.portrait.toString())

            }

            binding.rvCharacters.adapter = CharacterAdapter(names, portraits, ::characterSelected)

            with (sharedPreferences?.edit()) {

                this?.putString("NAMES", names.joinToString(", "))
                this?.putString("PORTRAITS", portraits.joinToString(", "))
                this?.apply()

            }

            viewModel.characters.removeObservers(viewLifecycleOwner)

        }

    }

    override fun onDestroyView() {

        super.onDestroyView()

        _binding = null

    }

    private fun characterSelected(character: Int) {

        val action = FragmentMainDirections.actionFragmentMainToFragmentDetail(characters[character])

        findNavController().navigate(action)

    }

}