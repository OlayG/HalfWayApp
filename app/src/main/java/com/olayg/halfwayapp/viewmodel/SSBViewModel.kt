package com.olayg.halfwayapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.olayg.halfwayapp.repo.SSBRepo

class SSBViewModel : ViewModel() {

    val categories = liveData { emit(SSBRepo.getAllCharacters()) }

}