package com.davidlopez.sqlite02

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

//https://www.youtube.com/watch?v=SfLXirT31zs&list=PL0bfr51v6JJEh1xtggpg57wN6m5Us3cb1&index=50

class MiSQLite (context: Context) : SQLiteOpenHelper(
    context,"Usuarios.db",null,1)
{

    //creamos un companion objet para que no halla errores al introducir los datos

    companion object{

        val TABLA_USUARIOS ="usuarios"
        val CAMPO_ID ="_id"
        val CAMPO_NOMBRE ="nombre"
        val CAMPO_EMAIL ="email"
    }
    override fun onCreate(db: SQLiteDatabase?) {

        val ordenCreacion ="CREATE TABLE ${TABLA_USUARIOS}" +
                "(${CAMPO_ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "${CAMPO_NOMBRE} TEXT, ${CAMPO_EMAIL} TEXT)"

        db!!.execSQL(ordenCreacion)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        val ordenBorrado ="DROP TABLE IF EXISTS ${TABLA_USUARIOS}"

        db!!.execSQL(ordenBorrado)

        onCreate(db)

    }

    fun insertDato(nombre:String,email:String){
        val datos =ContentValues()
        datos.put(CAMPO_NOMBRE,nombre)
        datos.put(CAMPO_EMAIL,email)

        val db=this.writableDatabase
        db.insert(TABLA_USUARIOS,null,datos)
       db.close()
    }

    //https://www.youtube.com/watch?v=skBsS1fOKLY&list=PL0bfr51v6JJEh1xtggpg57wN6m5Us3cb1&index=52

    fun borrarDato(id:Int): Int {

        val args = arrayOf(id.toString())

        val db=this.writableDatabase
        val borrados=db.delete(TABLA_USUARIOS,"${CAMPO_ID}=?",args)

        db.close()
        return borrados
    }// ff

    fun modificarDato(id:Int,nombre:String,email:String){

        val args = arrayOf(id.toString())

        val datos =ContentValues()

        datos.put(CAMPO_NOMBRE,nombre)
        datos.put(CAMPO_EMAIL,email)

        val db=this.writableDatabase
        db.update(TABLA_USUARIOS,datos,"${CAMPO_ID}=?",args)
        db.close()
    }


}