package com.davidlopez.sqlite02

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.davidlopez.sqlite02.databinding.ActivityListaBinding
import com.davidlopez.sqlite02.databinding.ActivityRecyclerBinding

class RecyclerActivity : AppCompatActivity() {

    //lateinit var binding: ActivityListaBinding

    lateinit var binding:ActivityRecyclerBinding
    lateinit var usuariosDBHelper:MiSQLite
    lateinit var db:SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding= ActivityListaBinding.inflate(layoutInflater)

        binding=ActivityRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        usuariosDBHelper= MiSQLite(this)

        db=usuariosDBHelper.readableDatabase
        val cursor:Cursor=db.rawQuery(
            "SELECT * FROM usuarios ",null)

        val adaptador = RecyclerViewAdapter()
        adaptador.RecyclerViewAdapter(this,cursor)

        binding.rvDatos.setHasFixedSize(true)
        binding.rvDatos.layoutManager=LinearLayoutManager(this)
        binding.rvDatos.adapter=adaptador
    }

    override fun onDestroy() {
        super.onDestroy()
        db.close()
    }

}