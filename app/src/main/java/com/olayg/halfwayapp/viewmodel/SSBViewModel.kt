package com.olayg.halfwayapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.olayg.halfwayapp.repo.SSBRepo

class SSBViewModel(application: Application) : AndroidViewModel(application) {

    val categories = liveData { emit(SSBRepo.getAllCharacters(application))
      //  .also { SSBRepo.saveData(application) }
    }

}