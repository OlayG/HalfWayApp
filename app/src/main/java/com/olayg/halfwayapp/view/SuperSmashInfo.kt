package com.olayg.halfwayapp.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.olayg.halfwayapp.adapter.GfyAdapter
import com.olayg.halfwayapp.constants.Constants
import com.olayg.halfwayapp.databinding.FragmentSuperSmashInfoBinding

class SuperSmashInfo : Fragment() {
    private var _binding: FragmentSuperSmashInfoBinding? = null
    private val binding get() = _binding!!

//    private val args by navArgs<SuperSmashInfoArgs>()

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
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val gifStringList = sharedPref?.getStringSet(Constants.gifList, mutableSetOf<String>())?.toList()
        rvGifs.adapter =
            gifStringList?.let {
                GfyAdapter(
                    it
                )
            }
        if (gifStringList.isNullOrEmpty()) {
            rvGifs.isVisible = false
            tvEmptyGifs.isVisible = true
        }
        tvName.text = sharedPref?.getString(Constants.charName, "Default Name")
        tvDescription.text = sharedPref?.getString(Constants.charGuide, "Default guide...")
        btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}