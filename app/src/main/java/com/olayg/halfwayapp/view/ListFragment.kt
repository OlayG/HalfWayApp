package com.olayg.halfwayapp.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.internal.ContextUtils.getActivity
import com.olayg.halfwayapp.R
import com.olayg.halfwayapp.adapter.CharacterAdapter
import com.olayg.halfwayapp.databinding.ActivityMainBinding
import com.olayg.halfwayapp.databinding.FragmentListBinding
import com.olayg.halfwayapp.databinding.ItemCharacterBinding
import com.olayg.halfwayapp.model.custom.Character
import com.olayg.halfwayapp.model.response.Gif
import com.olayg.halfwayapp.viewmodel.SSBViewModel
import kotlin.coroutines.coroutineContext

class ListFragment : Fragment(){
    //private val binding by lazy { FragmentListBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<SSBViewModel>()
  //  var controller = sel
    //val controer = findNavController()
   // ListFragment.controller = findNavController()


    private var _binding: FragmentListBinding?=  null
    private val binding get() = _binding!!

    private val binding2 by lazy { ItemCharacterBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentListBinding.inflate(inflater, container, false)
        .also { _binding = it }.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.categories.observe(viewLifecycleOwner) { characters ->
            Log.d("main","here")


            binding.rvCharacters.adapter = CharacterAdapter(characters, ::characterSelected)
            Log.d("main",binding.rvCharacters.adapter.toString())

            //(binding.rvCharacters.adapter as CharacterAdapter).CharacterViewHolder.passGif
            // Log.d("main",(binding.rvCharacters.adapter as CharacterAdapter).characters.size.toString())
           // binding.rvCharacters.findContainingItemView(View.inflate(R.layout.item_character))
        }

        binding.rvCharacters.findContainingViewHolder(view)?.itemView?.setOnClickListener(){}
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }*/

    private fun characterSelected(character: Character)= with(findNavController()) {
         Log.d("main","cs")
        Log.d("select",character.name!!)
        //val controller = findNavController()
        val action = ListFragmentDirections.toDetails(character)
       // controller.
        navigate(action)
        // return character as Unit
    }

   // fun getControl() : NavController  = controller

    companion object{


        lateinit var passChar : Character//  = findNavController(context as Fragment)
        fun selected(character: Character){
            Log.d("Comp", "here")

            passChar = character



        }
    }
}