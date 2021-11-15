package com.olayg.halfwayapp.view

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.olayg.halfwayapp.R
import com.olayg.halfwayapp.adapter.CharacterAdapter
import com.olayg.halfwayapp.databinding.ActivityMainBinding
import com.olayg.halfwayapp.model.custom.Character
import com.olayg.halfwayapp.viewmodel.SSBViewModel

class MainActivity : AppCompatActivity(R.layout.activity_main){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
          supportActionBar?.hide()
    }
}