package com.olayg.halfwayapp.repo

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.olayg.halfwayapp.model.custom.Character
import com.olayg.halfwayapp.model.response.CharacterResponse
import com.olayg.halfwayapp.model.response.Gif
import com.olayg.halfwayapp.model.response.Image
import com.olayg.halfwayapp.repo.remote.RetrofitInstance

object SSBRepo {

    private val smashBrosUnofficialService by lazy { RetrofitInstance.smashBrosUnofficialService }
    private val smashLoungeService by lazy { RetrofitInstance.smashLoungeService }

    suspend fun getAllCharacters(application: Application) : List<Character> {
        Log.d("Repo","getImage")
        val sharedPreferences: SharedPreferences =
            application.getSharedPreferences("share", Context.MODE_PRIVATE)!!
        val size: Int = sharedPreferences.getInt("size", 0)
        if (size == 0){
            Log.d("SAVING", "Nothing Saved yet")
            return saveData(application)
        }
        else{
            Log.d("SAVING", size.toString())
            return loadData(size,application)
        }
    }

    private suspend fun getImage(character: CharacterResponse) = try {
        Log.d("Repo",character.name)
        smashBrosUnofficialService.getAllCharacters(character.name).firstOrNull()?.image
        //Log.d("Repo","getImage")
    } catch (ex: Exception) {
        null
    }

    fun loadData(size : Int, application: Application) : List<Character> {
        val newList = mutableListOf<Character>()
        Log.d("SAVING","LOADING")

        val sharedPreferences: SharedPreferences =
            application.getSharedPreferences("share", Context.MODE_PRIVATE)!!
        val names = sharedPreferences.getString("nameList","Zelda")!!.split(",")
        sharedPreferences.apply {
            for(index in 0 until size)
            {
                val gifs = mutableListOf<Gif>()
                val sizeG = getInt(names[index]+"gifSize",0)
                for(indexG in 0 until sizeG){
                    val url = getString(names[index]+"url","na")
                    val desc = getString(names[index]+"description","na")
                    val gif = Gif(url!!,desc!!)
                    gifs.add(gif)
                }

                val image = Image(getString(names[index]+"icon","na")!!, "na")
                val name = getString(names[index]+"name","na")
                Log.d("SAVING","LOADING "+name)
                val char = Character(name,image,"na","na","na",gifs)
                newList.add(char)
            }
        }

        return newList
    }

    suspend fun saveData(application: Application) :List<Character> {

        val newList = repo()
        val size: Int = newList.size
        var nameList : String = ""

        for (i in 0 until size){
            nameList = nameList+newList[i].name+","
        }


            val sharedPreferences: SharedPreferences =
                application.getSharedPreferences("share", Context.MODE_PRIVATE)!!
            val editor = sharedPreferences.edit()
            editor.putInt("size",size).apply()
        editor.putString("nameList",nameList).apply()
        for (index in 0 until size){
            val saverName = newList[index].name.toString()
        editor.apply {


                putString(saverName+"name",newList[index].name.toString())
                Log.d("SAVING", newList[index].name.toString())
                putString(saverName+"icon",newList[index].image?.icon.toString())
                val sizeG = newList[index].gifs.size
                putInt(saverName+"gifSize",sizeG)
                for (indexG in 0 until sizeG){
                    putString(saverName+"url",newList[index].gifs[indexG]?.url.toString())
                    putString(saverName+"description",newList[index].gifs[indexG]?.description.toString())
                }
            }.apply()
        }
        return newList
    }

    suspend fun repo()= try {
        Log.d("Repo","getImage")
        smashLoungeService.getAllCharacters().map { characterResponse ->
            Character.convertToCharacter(characterResponse, getImage(characterResponse))
        }

    } catch (ex: Exception) {
        emptyList()
    }
}