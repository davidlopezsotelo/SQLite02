package com.davidlopez.sqlite02

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CursorAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.davidlopez.sqlite02.databinding.ActivityListaBinding
import com.davidlopez.sqlite02.databinding.ActivityListaBinding.inflate
import com.davidlopez.sqlite02.databinding.ActivityMainBinding
import com.davidlopez.sqlite02.databinding.ItemListviewBinding

class ListaActivity : AppCompatActivity(){

    lateinit var binding: ActivityListaBinding
    lateinit var usuariosDBHelper:MiSQLite

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= inflate(layoutInflater)
        setContentView(binding.root)

        usuariosDBHelper = MiSQLite(this)

        val db: SQLiteDatabase = usuariosDBHelper.readableDatabase
        // preparamos el cursor que leera los datos
        val cursor = db.rawQuery("SELECT * FROM  usuarios",null)

        val adaptador=CursorAdapterListView(this,cursor)
        binding.lvDatos.adapter=adaptador

        db.close()
    }

    //https://www.youtube.com/watch?v=V2O_DTFFPiQ&list=PL0bfr51v6JJEh1xtggpg57wN6m5Us3cb1&index=53

    inner class CursorAdapterListView(context: Context, cursor :Cursor): CursorAdapter(context, cursor,
        FLAG_REGISTER_CONTENT_OBSERVER) {

        override fun newView(context: Context?, cursor: Cursor?, parent: ViewGroup?): View {

            val inflater=LayoutInflater.from(context)
            return inflater.inflate(R.layout.item_listview, parent,false)
        }

        override fun bindView(view: View?, context: Context?, cursor: Cursor?) {
            val bindingItems=ItemListviewBinding.bind(view!!)
            bindingItems.tvItemNombre.text=cursor!!.getString(1)
            bindingItems.tvItemEmail.text= cursor.getString(2)

            view.setOnClickListener {
                Toast.makeText(this@ListaActivity,"${bindingItems.tvItemNombre.text}, " +
                        "${bindingItems.tvItemEmail.text}",
                Toast.LENGTH_SHORT).show()
            }
        }
    }
}