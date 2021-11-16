package com.olayg.halfwayapp.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.olayg.halfwayapp.R
import com.olayg.halfwayapp.adapter.CharacterAdapter
import com.olayg.halfwayapp.databinding.FragmentMainBinding
import com.olayg.halfwayapp.model.custom.Character
import com.olayg.halfwayapp.util.readChars
import com.olayg.halfwayapp.util.writeChars
import com.olayg.halfwayapp.viewmodel.SSBViewModel

class MainFragment : Fragment() {
    private val TAG = "MainFragment"
    private val gson = Gson()
    private lateinit var binding: FragmentMainBinding
    private val viewModel: SSBViewModel by activityViewModels()
    private var sharedPref: SharedPreferences? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        sharedPref = activity?.getSharedPreferences(
            getString(R.string.characters_preference), Context.MODE_PRIVATE
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPref?.apply {
            Log.d(TAG, "onViewCreated: ${this.readChars(context).toString()}")
            if (this.readChars(context).isNullOrEmpty()) {
                Log.d(TAG, "onViewCreated: ${this.readChars(context).toString()}")
                viewModel.categories.observe(requireActivity()) { characters ->
                    binding.rvCharacters.adapter = CharacterAdapter(characters, ::characterSelected)
                    val jsonCharacters = gson.toJson(characters)
                    Log.d(TAG, "onViewCreated: $jsonCharacters")
                    sharedPref?.writeChars(context, jsonCharacters)
                    Log.d(TAG, "onViewCreated: ${characters.size}")
                }
            }
            else {
                val characters: List<Character> = gson.fromJson(this.readChars(context), object : com.google.gson.reflect.TypeToken<List<Character>>() {}.type)
                binding.rvCharacters.adapter = CharacterAdapter(
                    characters,
                    ::characterSelected
                )
            }
        }
    }

    private fun characterSelected(character: Character) {
        Log.d(TAG, "characterSelected: $character")
    }

}