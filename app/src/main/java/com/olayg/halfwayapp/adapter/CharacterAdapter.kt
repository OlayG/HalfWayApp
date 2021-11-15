package com.olayg.halfwayapp.adapter

import android.util.Log
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.olayg.halfwayapp.databinding.ItemCharacterBinding
import com.olayg.halfwayapp.model.custom.Character
import com.olayg.halfwayapp.util.layoutInflater
import com.olayg.halfwayapp.util.loadUrl
import com.olayg.halfwayapp.view.MainFragmentDirections

class CharacterAdapter(
    private val characters: List<Character>,
    private val selectedCharacter: (Character) -> Unit
) : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ) = CharacterViewHolder.getInstance(parent).apply {
        itemView.setOnClickListener { selectedCharacter(characters[adapterPosition]) }
    }
    private val TAG = "CharacterAdapter"
    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.loadCharacter(characters[position])
        val action =
            characters[position].let {
                MainFragmentDirections.actionMainFragmentToDetailFragment(
                    it
                )
            }
        holder.itemView.setOnClickListener {
            action.let { character -> it.findNavController().navigate(character) }
            Log.d(TAG, "onBindViewHolder:${characters[position].name} ")

        }
    }

    override fun getItemCount() = characters.size

    class CharacterViewHolder(
        private val binding: ItemCharacterBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        private val TAG = "CharacterAdapter"
        fun loadCharacter(character: Character) = with(binding) {
            tvName.text = character.name
            ivIcon.loadUrl(character.image?.icon)
            Log.d(TAG, "loadCharacter: {${character.name}}")
        }


        companion object {
            fun getInstance(parent: ViewGroup) = ItemCharacterBinding.inflate(
                parent.layoutInflater, parent, false
            ).run { CharacterViewHolder(this) }
        }
    }
}