package com.davidlopez.sqlite02

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

//https://www.youtube.com/watch?v=SfLXirT31zs&list=PL0bfr51v6JJEh1xtggpg57wN6m5Us3cb1&index=50

class MiSQLite (context: Context) : SQLiteOpenHelper(
    context,"Usuarios.db",null,1)
{
    override fun onCreate(db: SQLiteDatabase?) {

        val ordenCreacion ="CREATE TABLE usuarios" +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT, email TEXT)"

        db!!.execSQL(ordenCreacion)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        val ordenBorrado ="DROP TABLE IF EXISTS usuarios"

        db!!.execSQL(ordenBorrado)

        onCreate(db)

    }

    fun insertDato(nombre:String,email:String){
        val datos =ContentValues()
        datos.put("nombre",nombre)
        datos.put("email",email)

        val db=this.writableDatabase
        db.insert("usuarios",null,datos)
       db.close()
    }

    //https://www.youtube.com/watch?v=skBsS1fOKLY&list=PL0bfr51v6JJEh1xtggpg57wN6m5Us3cb1&index=52

    fun borrarDato(id:Int): Int {

        val args = arrayOf(id.toString())

        val db=this.writableDatabase
        val borrados=db.delete("usuarios","_id =?",args)

        db.close()
        return borrados
    }// ff

    fun modificarDato(id:Int,nombre:String,email:String){

        val args = arrayOf(id.toString())

        val datos =ContentValues()

        datos.put("nombre",nombre)
        datos.put("email",email)

        val db=this.writableDatabase
        db.update("usuarios",datos,"_id =?",args)
        db.close()
    }


}