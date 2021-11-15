package com.olayg.halfwayapp.adapter

import android.app.Activity
import android.text.method.TextKeyListener.clear
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.internal.ContextUtils.getActivity
import com.olayg.halfwayapp.databinding.ItemCharacterBinding
import com.olayg.halfwayapp.model.custom.Character
import com.olayg.halfwayapp.model.response.Gif
import com.olayg.halfwayapp.util.layoutInflater
import com.olayg.halfwayapp.util.loadUrl
import com.olayg.halfwayapp.view.ListFragment
import com.olayg.halfwayapp.view.ListFragmentDirections
import com.olayg.halfwayapp.view.MainActivity
import java.util.Collections.addAll

class CharacterAdapter(
    private val characters: List<Character>,
    private val selectedCharacter: (Character) -> Unit
) : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ) = CharacterViewHolder.getInstance(parent).apply {
        itemView.setOnClickListener { selectedCharacter(characters[adapterPosition]) }
        Log.d("adapter","OCV")
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.loadCharacter(characters[position])
        Log.d("adapter","OBV")
    }

    override fun getItemCount() = characters.size


    class CharacterViewHolder(
        private val binding: ItemCharacterBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun loadCharacter(character: Character) = with(binding) {
            Log.d("adapter","load char"+character.name )
            tvName.text = character.name
            ivPhoto.loadUrl(character.image?.icon)
          //  ivPhoto.setOnClickListener(){
              //  Log.d("Click",character.name ?: "no name")
               // ivPhoto.isSelected = true
               // ListFragment.selected(character) }

        }

        companion object {
            fun getInstance(parent: ViewGroup) = ItemCharacterBinding.inflate(
                parent.layoutInflater, parent, false
            ).run { CharacterViewHolder(this)
            }



        }
    }
}