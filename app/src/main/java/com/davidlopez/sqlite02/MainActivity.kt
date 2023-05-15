package com.davidlopez.sqlite02

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isNotEmpty
import com.davidlopez.sqlite02.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var usuariosDBHelper:MiSQLite

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // acceso a clase SQLite

        usuariosDBHelper = MiSQLite(this)

//AÑADIR DATOS -------------------------------------------------------------------------------------

        binding.btGuardar.setOnClickListener {

            if (binding.etNombre.text.isNotBlank() &&
                    binding.etEmail.text.isNotBlank()) {

                usuariosDBHelper.insertDato(
                    binding.etNombre.text.toString(),
                    binding.etEmail.text.toString())

                //vaciar datos despues de guardar
                binding.etNombre.text.clear()
                binding.etEmail.text.clear()

                Toast.makeText(this, "Usuario Guardaro",Toast.LENGTH_LONG).show()

            }else Toast.makeText(this, "No se ha podido guardar!!!",Toast.LENGTH_LONG).show()

        }//Fin boton

// LEER DATOS----------------------------------------------------------------------------------------

        //https://www.youtube.com/watch?v=4F5CXoYsbL8&list=PL0bfr51v6JJEh1xtggpg57wN6m5Us3cb1&index=51

        binding.btConsulta.setOnClickListener {

            binding.tvConsulta.text=""

            val db:SQLiteDatabase= usuariosDBHelper.readableDatabase

            // preparamos el cursor que leera los datos
            val cursor = db.rawQuery("SELECT * FROM  usuarios",null)


            if (cursor.moveToFirst()){//movemos el cursor al principio
                do{

                    binding.tvConsulta.append (cursor.getInt(0).toString() + ": ")
                    binding.tvConsulta.append (cursor.getString(1).toString() + ", ")
                    binding.tvConsulta.append (cursor.getString(2).toString() + "\n")

                }while (cursor.moveToNext())
            }

    }//FIN boton

//MODIFICAR DATOS----------------------------------------------------------------------------------


        binding.btModificar.setOnClickListener {

            if (binding.etNombre.text.isNotBlank() &&
                binding.etEmail.text.isNotBlank() && binding.etId.text.isNotBlank()) {

                usuariosDBHelper.modificarDato(
                    binding.etId.text.toString().toInt(),
                    binding.etNombre.text.toString(),
                    binding.etEmail.text.toString())

                //vaciar datos despues de guardar
                binding.etNombre.text.clear()
                binding.etEmail.text.clear()
                binding.etId.text.clear()


                Toast.makeText(this, "Usuario Modificado",Toast.LENGTH_LONG).show()

            }else Toast.makeText(this, "Todos los campos deven estar completos!!",Toast.LENGTH_LONG).show()


        }//fin boton



//BORRAR DATOS----------------------------------------------------------------------------------------

        binding.btBorrar.setOnClickListener {

            var cantidad=0

            if (binding.etId.text.isNotBlank()) {

                cantidad = usuariosDBHelper.borrarDato(
                    binding.etId.toString().toInt() )

                binding.etId.text.clear()

                Toast.makeText(this, "Datos borrados: $cantidad",Toast.LENGTH_LONG).show()
            }
        }//fin boton

//CONSULTAR LISTA-----------------------------------------------------------------------------

        binding.btList.setOnClickListener {

            val intentListView=Intent(this,ListaActivity::class.java)
            startActivity(intentListView)

        }
}
}