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
            val iconUrl = if (character.name == "Mr. Game And Watch") "https://pm1.narvii.com/6128/0aa7ca2a64dcfd893eb97ac36d3ce6c99b755b81_hq.jpg" else character.image?.icon
            tvName.text = character.name
            ivIcon.loadUrl(iconUrl)
            Log.d(TAG, "loadCharacter: {$iconUrl}")
        }


        companion object {
            fun getInstance(parent: ViewGroup) = ItemCharacterBinding.inflate(
                parent.layoutInflater, parent, false
            ).run { CharacterViewHolder(this) }
        }
    }
}