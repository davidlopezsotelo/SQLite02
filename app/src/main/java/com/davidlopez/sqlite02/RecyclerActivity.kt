package com.davidlopez.sqlite02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.davidlopez.sqlite02.databinding.ActivityListaBinding

class RecyclerActivity : AppCompatActivity() {

    lateinit var binding: ActivityListaBinding
    lateinit var usuariosDBHelper:MiSQLite

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityListaBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}