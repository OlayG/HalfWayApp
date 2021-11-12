package com.olayg.halfwayapp.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.olayg.halfwayapp.databinding.ItemCharacterBinding
import com.olayg.halfwayapp.model.custom.Character
import com.olayg.halfwayapp.util.layoutInflater
import com.olayg.halfwayapp.util.loadUrl

class CharacterAdapter(
    private val characters: List<Character>,
    private val selectedCharacter: (Character) -> Unit
) : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ) = CharacterViewHolder.getInstance(parent).apply {
        itemView.setOnClickListener { selectedCharacter(characters[adapterPosition]) }
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.loadCharacter(characters[position])
    }

    override fun getItemCount() = characters.size.minus(characters.size)

    class CharacterViewHolder(
        private val binding: ItemCharacterBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun loadCharacter(character: Character) = with(binding) {
            tvName.text = character.name
        }

        companion object {
            fun getInstance(parent: ViewGroup) = ItemCharacterBinding.inflate(
                parent.layoutInflater, parent, false
            ).run { CharacterViewHolder(this) }
        }
    }
}