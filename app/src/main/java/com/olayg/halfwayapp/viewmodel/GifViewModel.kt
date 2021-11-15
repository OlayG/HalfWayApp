package com.olayg.halfwayapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.olayg.halfwayapp.repo.GifRepo
import com.olayg.halfwayapp.repo.SSBRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GifViewModel(name : String) : ViewModel() {
    val gifsData = liveData { emit(GifRepo.getImages(name)) }

}