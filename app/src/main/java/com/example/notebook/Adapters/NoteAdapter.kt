package com.example.notebook.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.notebook.Fragments.HomeFragmentDirections
import com.example.notebook.R
import com.example.notebook.model.Notes

class NoteAdapter(val context :Context,private var notes:ArrayList<Notes>) :RecyclerView.Adapter<NoteAdapter.MyViewHolder>() {

  fun SearchList(newList:ArrayList<Notes>){
      notes = newList
      notifyDataSetChanged()
  }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.note_item,parent,false)
        return  MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = notes[position]
        holder.title.text =data.title
        holder.notes.text = data.notes
        holder.dateTime.text = data.date_time

        holder.itemView.setOnClickListener{

          //  val action = HomeFragmentDirections.actionHomeFragmentToEditFragment(data)
            val action= HomeFragmentDirections.actionHomeFragmentToEditFragment(data)
            Navigation.findNavController(it).navigate(action)
        }

    }

    override fun getItemCount(): Int {
      return  notes.size
    }
    inner  class  MyViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        var title:TextView
        var notes:TextView
        var dateTime:TextView

        init {
            title= itemView.findViewById(R.id.title)
            notes = itemView.findViewById(R.id.notes)
            dateTime = itemView.findViewById(R.id.dateTime)
        }


    }


}