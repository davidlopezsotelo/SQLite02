package com.davidlopez.sqlite02

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.davidlopez.sqlite02.databinding.ItemRecyclreviewBinding

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(){

   lateinit var context :Context
   lateinit var cursor :Cursor

    @SuppressLint("NotConstructor")
    fun RecyclerViewAdapter(context: Context, cursor: Cursor){
        this.context=context
        this.cursor=cursor
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.item_recyclreview,parent,false))
    }

    override fun getItemCount(): Int = cursor.count

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        cursor.moveToPosition(position)

        holder.tvNombre.text=cursor.getString(1)
        holder.tvEmail.text=cursor.getString(2)

    }
    //  creamos una clase interna para crear el viewholder
    inner class ViewHolder :RecyclerView.ViewHolder {

         val tvNombre :TextView
         val tvEmail :TextView

        // implementamos el constructor necesario

        constructor(view: View):super(view){

            //creamnos la conexion con los ficheros xml
            val bindingItemRV =ItemRecyclreviewBinding.bind(view)

            tvNombre = bindingItemRV.tvItemNom
            tvEmail = bindingItemRV.tvItemEm

            view.setOnClickListener{
                Toast.makeText(context,
                "${tvNombre.text}, ${tvEmail.text}",
                Toast.LENGTH_SHORT).show()
            }
        }
    }
}