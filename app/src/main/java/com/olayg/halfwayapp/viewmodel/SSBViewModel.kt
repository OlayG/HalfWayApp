package com.olayg.halfwayapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.olayg.halfwayapp.repo.SSBRepo

class SSBViewModel : ViewModel() {

    val characters = liveData { emit(SSBRepo.getAllCharacters()) }

}