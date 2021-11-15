package com.olayg.halfwayapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.olayg.halfwayapp.R
import com.olayg.halfwayapp.adapter.CharacterAdapter
import com.olayg.halfwayapp.databinding.FragmentSuperSmashListBinding
import com.olayg.halfwayapp.model.custom.Character
import com.olayg.halfwayapp.viewmodel.SSBViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SuperSmashList.newInstance] factory method to
 * create an instance of this fragment.
 */
class SuperSmashList : Fragment() {
    private var _binding: FragmentSuperSmashListBinding? = null
    private val binding get() = _binding!!
    private val ssbViewModel by activityViewModels<SSBViewModel>()

//    private val args by navArgs<GIFListFragmentArgs>()

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

    private fun characterSelected(character: Character) {
    }

}