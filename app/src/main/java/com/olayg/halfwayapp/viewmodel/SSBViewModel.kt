package com.olayg.halfwayapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.olayg.halfwayapp.repo.SSBRepo

private const val TAG = "SSBViewModel"
class SSBViewModel : ViewModel() {
    val categories = liveData {
        emit(SSBRepo.getAllCharacters()) }
}