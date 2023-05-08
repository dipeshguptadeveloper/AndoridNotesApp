package com.dkgtech.notes

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerNoteAdapter(val context: Context, val arrNote: ArrayList<NoteModel>) :
    RecyclerView.Adapter<RecyclerNoteAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtNoteTitle = itemView.findViewById<TextView>(R.id.txtNoteTitle)
        val txtNoteDescription = itemView.findViewById<TextView>(R.id.txtNoteDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.note_row, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return arrNote.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtNoteTitle.text = arrNote[position].noteTitle
        holder.txtNoteDescription.text = arrNote[position].noteDescription
    }
}