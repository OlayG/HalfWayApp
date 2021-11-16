package com.olayg.halfwayapp.repo

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.dataStoreFile
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.olayg.halfwayapp.model.custom.Character
import com.olayg.halfwayapp.model.response.CharacterResponse
import com.olayg.halfwayapp.model.response.Gif
import com.olayg.halfwayapp.model.response.Image
import com.olayg.halfwayapp.repo.remote.RetrofitInstance
import kotlinx.coroutines.flow.first

object SSBRepo {

    private val smashBrosUnofficialService by lazy { RetrofitInstance.smashBrosUnofficialService }
    private val smashLoungeService by lazy { RetrofitInstance.smashLoungeService }
    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "characters")

    private suspend fun dataStoreSave(ds : DataStore<Preferences>) :List<Character>{

        val newList = repo()
        val size: Int = newList.size
        var nameList : String = ""

        for (i in 0 until size){
            nameList = nameList+newList[i].name+","
        }

        ds.edit {
        it[intPreferencesKey("size")]= size
        it[stringPreferencesKey("nameList")]=nameList}
        for (index in 0 until size){
            val saverName = newList[index].name.toString()
            ds.edit {


                it[stringPreferencesKey(saverName+"name")] = newList[index].name.toString()
                Log.d("DS SAVING", newList[index].name.toString())
                        it[stringPreferencesKey(saverName+"icon")] = newList[index].image?.icon.toString()
                val sizeG = newList[index].gifs.size
                it[intPreferencesKey(saverName+"gifSize")] = sizeG
                for (indexG in 0 until sizeG){
                    it[stringPreferencesKey(saverName+"url")] = newList[index].gifs[indexG]?.url.toString()
                    it[stringPreferencesKey(saverName+"description")] = newList[index].gifs[indexG]?.description.toString()
                }
            }
        }
        return newList


    }

    private suspend fun dataStoreRead(size:Int, ds: DataStore<Preferences>) : List<Character>{
        val preferences = ds.data.first()



        val newList = mutableListOf<Character>()
        Log.d("DS SAVING","DS LOADING")


        val names = preferences[stringPreferencesKey("nameList")]!!.split(",")
        ds.edit {
            for(index in 0 until size)
            {
                val gifs = mutableListOf<Gif>()
                val sizeG = preferences[intPreferencesKey(names[index]+"gifSize")]
                for(indexG in 0 until sizeG!!){
                    val url = preferences[stringPreferencesKey(names[index]+"url")]
                    val desc = preferences[stringPreferencesKey(names[index]+"description")]
                    val gif = Gif(url!!,desc!!)
                    gifs.add(gif)
                }

                val image = Image(preferences[stringPreferencesKey(names[index]+"icon")]!!, "na")
                val name = preferences[stringPreferencesKey(names[index]+"name")]
                Log.d("DS SAVING","DS LOADING "+name)
                val char = Character(name,image,"na","na","na",gifs)
                newList.add(char)
            }
        }

        return newList
    }

    suspend fun getAllCharacters(application: Application) : List<Character> {
        Log.d("Repo","getImage")
        val dataStore = application.applicationContext.dataStore
        val pizza  = preferencesDataStore(name = "characters")

        /*val sharedPreferences: SharedPreferences =
            application.getSharedPreferences("share", Context.MODE_PRIVATE)!!
        val size: Int = sharedPreferences.getInt("size", 0)*/
        var size : Int = 0
        val preferences = dataStore.data.first()
        dataStore.edit { size = preferences[intPreferencesKey("size")]?:0 }

        if (size == 0){
            Log.d("SAVING", "Nothing Saved yet")
            //return saveData(application)
            return dataStoreSave(dataStore)
        }
        else{
            Log.d("SAVING", size.toString())
            //return loadData(size,application)
            return dataStoreRead(size,dataStore)
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